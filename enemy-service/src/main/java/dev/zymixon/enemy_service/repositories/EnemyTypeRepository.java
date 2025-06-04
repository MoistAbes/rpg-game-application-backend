package dev.zymixon.enemy_service.repositories;

import dev.zymixon.enemy_service.entities.EnemyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyTypeRepository extends JpaRepository<EnemyType, Long> {
}
