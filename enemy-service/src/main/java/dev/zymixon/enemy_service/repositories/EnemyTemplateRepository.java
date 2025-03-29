package dev.zymixon.enemy_service.repositories;

import dev.zymixon.enemy_service.entities.EnemyTemplate;
import dev.zymixon.enemy_service.entities.EnemyType;
import dev.zymixon.enemy_service.enums.EnemyTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemyTemplateRepository extends JpaRepository<EnemyTemplate, Long> {

//    @Query("SELECT t.id FROM EnemyTemplate t WHERE t.enemyTier in :tiers AND t.enemyTypes in :enemyTypes")
//    List<Long> findAllByTypeAndTier(@Param("enemyTypes") List<EnemyTypeEnum> enemyTypes, @Param("tiers") List<Integer> tiers);

//    @Query("SELECT t.id FROM EnemyTemplate t WHERE t.enemyTier IN :tiers AND t.enemyTypes IN :enemyTypeNames")
//    List<Long> findAllByTypeAndTier(@Param("enemyTypeNames") List<String> enemyTypeNames, @Param("tiers") List<Integer> tiers);

//    @Query("SELECT t.id FROM EnemyTemplate t WHERE t.enemyTier IN :tiers AND :enemyTypeName MEMBER OF t.enemyTypes")
//    List<Long> findAllByTypeAndTier(@Param("enemyTypeName") EnemyType enemyTypeName, @Param("tiers") List<Integer> tiers);
//
//    @Query("SELECT DISTINCT t.id FROM EnemyTemplate t JOIN t.enemyTypes et WHERE t.enemyTier IN :tiers AND et.type IN :enemyTypeNames")
//    List<Long> findAllByTypeAndTier(@Param("enemyTypeNames") List<EnemyType> enemyTypeNames, @Param("tiers") List<Integer> tiers);

    @Query("SELECT DISTINCT t.id FROM EnemyTemplate t JOIN t.enemyTypes et WHERE t.enemyTier IN :tiers AND et.type IN :enemyTypeNames")
    List<Long> findAllByTypeAndTier(@Param("enemyTypeNames") List<EnemyTypeEnum> enemyTypeNames, @Param("tiers") List<Integer> tiers);






}
