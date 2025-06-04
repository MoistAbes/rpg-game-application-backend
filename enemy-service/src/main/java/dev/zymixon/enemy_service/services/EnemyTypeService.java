package dev.zymixon.enemy_service.services;

import dev.zymixon.enemy_service.entities.EnemyType;
import dev.zymixon.enemy_service.repositories.EnemyTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemyTypeService {

    private final EnemyTypeRepository enemyTypeRepository;

    public EnemyTypeService(EnemyTypeRepository enemyTypeRepository) {
        this.enemyTypeRepository = enemyTypeRepository;
    }

    public List<EnemyType> getAllEnemyType() {
        return enemyTypeRepository.findAll();
    }
}
