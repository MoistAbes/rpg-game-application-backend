package dev.zymixon.enemy_service.services;

import dev.zymixon.enemy_service.enums.EnemyTypeEnum;
import dev.zymixon.enemy_service.repositories.EnemyTemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class EnemyTemplateService {

    private final EnemyTemplateRepository enemyTemplateRepository;

    public EnemyTemplateService(EnemyTemplateRepository enemyTemplateRepository) {
        this.enemyTemplateRepository = enemyTemplateRepository;
    }

    public List<Long> getAllEnemyTemplateIdsByRankAndTier(List<EnemyTypeEnum> enemyTypes, List<Integer> tiers) {

        return enemyTemplateRepository.findAllByTypeAndTier(enemyTypes, tiers);
    }


}
