package dev.zymixon.quest_service.repositories;

import dev.zymixon.quest_service.entities.BountyQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BountyQuestRepository extends JpaRepository<BountyQuest, Long> {

    @Query("SELECT bq FROM BountyQuest bq WHERE bq.characterId = :characterId")
    List<BountyQuest> findAllByCharacterId(@Param("characterId") Long characterId);
}
