package dev.zymixon.enemy_service.repositories;

import dev.zymixon.enemy_service.entities.EnemyInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyInstanceRepository extends JpaRepository<EnemyInstance, Long> {
}
