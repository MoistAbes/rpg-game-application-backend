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

            //generate item drops
            messageSenderService.sendItemDropGenerateRequest(combatRequest);

            int experienceGained = calculateExperienceGain(
                    combatRequest.getEnemyInstance().getLevel(),
                    combatRequest.getEnemyInstance().getTemplate().getEnemyTier(),
                    combatRequest.getEnemyInstance().getRank());


            System.out.println("Experience gained: " +  experienceGained);

            int currentExperience = combatRequest.getPlayerCharacter().getExperience();
            combatResult.setExperience(experienceGained);
            combatRequest.getPlayerCharacter().setExperience(currentExperience + experienceGained);


            //send updated character
            messageSenderService.sendUpdatedCharacter(combatRequest.getPlayerCharacter());


        }


        return combatResult;
    }


    private int calculatePlayerAttackDamage(CombatRequestDto combatRequest) {

        int playerAttack = combatRequest.getPlayerCharacter().getCharacterStats().getAttack();
        int enemyDefense = combatRequest.getEnemyInstance().getCurrentArmor();

        return Math.max(playerAttack - enemyDefense, 0);

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
