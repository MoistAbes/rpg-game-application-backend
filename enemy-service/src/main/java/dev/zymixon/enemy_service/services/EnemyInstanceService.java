package dev.zymixon.enemy_service.services;

import dev.zymixon.enemy_service.entities.EnemyInstance;
import dev.zymixon.enemy_service.entities.EnemyTemplate;
import dev.zymixon.enemy_service.enums.EnemyRank;
import dev.zymixon.enemy_service.enums.EnemyTypeEnum;
import dev.zymixon.enemy_service.exceptions.EnemyTemplateNotFoundException;
import dev.zymixon.enemy_service.models.GenerateEnemyRequest;
import dev.zymixon.enemy_service.repositories.EnemyInstanceRepository;
import dev.zymixon.enemy_service.repositories.EnemyTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EnemyInstanceService {

    private final EnemyInstanceRepository enemyInstanceRepository;
    private final EnemyTemplateRepository enemyTemplateRepository;

    public EnemyInstanceService(EnemyInstanceRepository enemyInstanceRepository, EnemyTemplateRepository enemyTemplateRepository) {
        this.enemyInstanceRepository = enemyInstanceRepository;
        this.enemyTemplateRepository = enemyTemplateRepository;
    }

    public EnemyInstance generateEnemyInstance(GenerateEnemyRequest generateEnemyRequest) {

        return createEnemyInstance(generateEnemyRequest);

    }



    private EnemyInstance createEnemyInstance(GenerateEnemyRequest generateEnemyRequest) {
        EnemyTemplate enemyTemplate = enemyTemplateRepository.findById(generateEnemyRequest.getEnemyTemplateId())
                .orElseThrow(() -> new EnemyTemplateNotFoundException("Enemy template with id: " + generateEnemyRequest.getEnemyTemplateId() + " not found"));

        //randomize enemy rank
        EnemyRank enemyRank = getEnemyRank();

        //get enemy level
        int enemyLevel = getRandomEnemyLevel(generateEnemyRequest.getMinEnemyLevel(), generateEnemyRequest.getMaxEnemyLevel());

        //get attribute multiplier based on enemy rank
        float attributeMultiplier = getEnemyStatsMultiplier(enemyRank, enemyLevel);

        //generate enemy instance
        EnemyInstance generatedEnemyInstance = EnemyInstance.builder()
                .level(enemyLevel)
                .currentArmor(Math.round(enemyTemplate.getArmor() * attributeMultiplier))
                .currentAttack(Math.round(enemyTemplate.getAttack() * attributeMultiplier))
                .currentHealth((long) Math.round(enemyTemplate.getHealth() * attributeMultiplier))
                .maxHealth((long) Math.round(enemyTemplate.getHealth() * attributeMultiplier))
                .rank(enemyRank)
                .template(enemyTemplate)
                .build();


        return enemyInstanceRepository.save(generatedEnemyInstance);

    }

    private EnemyRank getEnemyRank() {
        // Get a random index within the range of the enum values
        Random random = new Random();
        EnemyRank[] values = EnemyRank.values();
        int randomIndex = random.nextInt(values.length);

        // Return the random EnemyRank
        return values[randomIndex];
    }

    private float getEnemyStatsMultiplier(EnemyRank enemyRank, int enemyLevel) {
        // Get rank multiplier
        float rankMultiplier = getRankMultiplier(enemyRank);

        // Get level multiplier
        float levelMultiplier = getLevelMultiplier(enemyLevel);

        // Combine both multipliers (you can adjust the weighting if needed)
        return rankMultiplier * levelMultiplier;
    }

    private float getRankMultiplier(EnemyRank enemyRank) {
        float attributeMultiplier = 0f;

        switch (enemyRank) {
            case NORMAL -> attributeMultiplier = 1.0f;
            case ELITE -> attributeMultiplier = 1.1f;
            case CHAMPION -> attributeMultiplier = 1.2f;
            case BOSS -> attributeMultiplier = 1.3f;
            case MYTHIC -> attributeMultiplier = 1.5f;
            default -> throw new IllegalArgumentException("Unknown enemy rank: " + enemyRank);
        }

        return attributeMultiplier;
    }

    private float getLevelMultiplier(int enemyLevel) {
        return 1.0f + (enemyLevel - 1) * 0.20f; // Increase stats by 5% for each level
    }

    private Integer getRandomEnemyLevel(Integer minEnemyLevel, Integer maxEnemyLevel) {
        if (minEnemyLevel == null || maxEnemyLevel == null || minEnemyLevel > maxEnemyLevel) {
            throw new IllegalArgumentException("Invalid level range");
        }
        return ThreadLocalRandom.current().nextInt(minEnemyLevel, maxEnemyLevel + 1);
    }

}
