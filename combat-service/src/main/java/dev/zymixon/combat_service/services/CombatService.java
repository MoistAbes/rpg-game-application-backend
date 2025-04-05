package dev.zymixon.combat_service.services;

import dev.zymixon.combat_service.controllers.CombatController;
import dev.zymixon.combat_service.dto.CombatLog;
import dev.zymixon.combat_service.dto.CombatResult;
import dev.zymixon.combat_service.enums.EnemyRank;
import dev.zymixon.combat_service.models.CombatRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CombatService {

    private static final Logger logger = LoggerFactory.getLogger(CombatService.class);

    private final MessageSenderService messageSenderService;

    public CombatService(MessageSenderService messageSenderService) {
        this.messageSenderService = messageSenderService;
    }

    public CombatResult calculateCombat(CombatRequestDto combatRequest) {

        CombatResult combatResult = new CombatResult();
        List<CombatLog> combatLogs = new ArrayList<>();


        boolean isCombatOver = false;
        boolean isPlayerTurn = true;
        int turnCounter = 1;

        while (!isCombatOver) {

            if (isPlayerTurn) {
                //player character turn

                playerAttack(combatRequest, combatLogs, turnCounter);
                isPlayerTurn = false;


            }else {
                //enemy turn
                enemyAttack(combatRequest, combatLogs, turnCounter);
                isPlayerTurn = true;
            }

            turnCounter++;

            if (combatRequest.getPlayerCharacter().getCharacterStats().getCurrentHealth() <= 0) {
                isCombatOver = true;
                combatResult.setSuccess(Boolean.FALSE);
            }

            if (combatRequest.getEnemyInstance().getCurrentHealth() <= 0) {
                isCombatOver = true;
                combatResult.setSuccess(Boolean.TRUE);
            }
        }

        combatResult.setCombatLogs(combatLogs);

        //if win calculate exp gain and update character stats
        if (combatResult.isSuccess()) {

            // Start both tasks in parallel
            long startDropTime = System.nanoTime();
            CompletableFuture<List<Long>> droppedItemsFuture = messageSenderService.sendItemDropGenerateRequest(combatRequest)
                    .whenComplete((result, throwable) -> {
                        long endDropTime = System.nanoTime();
                        System.out.println("sendItemDropGenerateRequest took: " + (endDropTime - startDropTime) / 1_000_000 + " ms");
                    });

            long startCharacterUpdateTime = System.nanoTime();
            CompletableFuture<Void> characterUpdateFuture = CompletableFuture.runAsync(() -> {
                int experienceGained = calculateExperienceGain(
                        combatRequest.getEnemyInstance().getLevel(),
                        combatRequest.getEnemyInstance().getTemplate().getEnemyTier(),
                        combatRequest.getEnemyInstance().getRank());

                int currentExperience = combatRequest.getPlayerCharacter().getExperience();
                combatResult.setExperience(experienceGained);
                combatRequest.getPlayerCharacter().setExperience(currentExperience + experienceGained);

                messageSenderService.sendUpdatedCharacter(combatRequest.getPlayerCharacter());

                long endCharacterUpdateTime = System.nanoTime();
                System.out.println("Character update process took: " + (endCharacterUpdateTime - startCharacterUpdateTime) / 1_000_000 + " ms");
            });

            // Wait for both to complete
            List<Long> droppedItems = droppedItemsFuture.join();
            combatResult.setDroppedItems(droppedItems);
            characterUpdateFuture.join();
        }

        return combatResult;
    }


    private int calculatePlayerAttackDamage(CombatRequestDto combatRequest) {

        int playerAttack = combatRequest.getPlayerCharacter().getCharacterStats().getAttack();
        int enemyDefense = combatRequest.getEnemyInstance().getCurrentArmor();

        float critChance = combatRequest.getPlayerCharacter().getCharacterStats().getCriticalChance();
        float critDamageMultiplier = combatRequest.getPlayerCharacter().getCharacterStats().getCriticalDamage() / 100.0f;

        // Generate a random number between 0 and 100
        double randomRoll = Math.random() * 100;

        // Determine if it's a crit
        boolean isCriticalHit = randomRoll <= critChance;

        // Apply crit multiplier if it's a critical hit
        int finalDamage = (int) ((isCriticalHit ? playerAttack * critDamageMultiplier : playerAttack) - enemyDefense);

        return Math.max(finalDamage, 0);
    }

    private void playerAttack(CombatRequestDto combatRequest, List<CombatLog> combatLogs, int turnCounter) {
        int playerAttackDamage = calculatePlayerAttackDamage(combatRequest);
        Long enemyCurrentHealth = combatRequest.getEnemyInstance().getCurrentHealth();
        Long enemyResultHealth = enemyCurrentHealth - playerAttackDamage;

        //reduce enemy health by attack value
        combatRequest.getEnemyInstance().setCurrentHealth(enemyResultHealth);

        //combat logs
        combatLogs.add(CombatLog.builder()
                .turn(turnCounter)
                .log(combatRequest.getPlayerCharacter().getName() + "  attacked for: " + playerAttackDamage)
                .enemyCurrentHealth(enemyResultHealth)
                .playerCurrentHealth(combatRequest.getPlayerCharacter().getCharacterStats().getCurrentHealth())
                .build());

    }

    private int calculateEnemyAttackDamage(CombatRequestDto combatRequest) {
        int enemyAttack = combatRequest.getEnemyInstance().getCurrentAttack();
        int playerDefense = combatRequest.getPlayerCharacter().getCharacterStats().getDefense();

        return Math.max(enemyAttack - playerDefense, 0);
    }

    private void enemyAttack(CombatRequestDto combatRequest, List<CombatLog> combatLogs, int turnCounter) {
        int enemyAttackDamage = calculateEnemyAttackDamage(combatRequest);
        int playerCurrentHealth = combatRequest.getPlayerCharacter().getCharacterStats().getCurrentHealth();
        int playerResultHealth = playerCurrentHealth - enemyAttackDamage;


        combatRequest.getPlayerCharacter().getCharacterStats().setCurrentHealth(playerResultHealth);

        //        combatLogs.add("Enemy attacked for: " + enemyAttackDamage);
        combatLogs.add(CombatLog.builder()
                .turn(turnCounter)
                .log("Enemy attacked for: " + enemyAttackDamage)
                .enemyCurrentHealth(combatRequest.getEnemyInstance().getCurrentHealth())
                .playerCurrentHealth(playerResultHealth)
                .build());
    }

    private int calculateExperienceGain(int enemyLevel, int enemyTier, EnemyRank enemyRank) {
        // Base experience is based on the enemy level
        int baseExp = enemyLevel * 10;

        // Tier multiplier (scales from 1.0 to 1.8 for tiers 1-5)
        double tierMultiplier = 1.0 + (enemyTier - 1) * 0.2;

        // Rank multipliers
        double rankMultiplier;
        switch (enemyRank) {
            case ELITE -> rankMultiplier = 1.5;
            case CHAMPION -> rankMultiplier = 2.0;
            case BOSS -> rankMultiplier = 3.0;
            case MYTHIC -> rankMultiplier = 5.0;
            default -> rankMultiplier = 1.0;
        }

        // Final experience calculation
        return (int) (baseExp * tierMultiplier * rankMultiplier);
    }


}
