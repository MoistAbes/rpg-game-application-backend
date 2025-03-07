package dev.zymixon.character_service.repositories;

import dev.zymixon.character_service.entities.CharacterStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterStatsRepository extends JpaRepository<CharacterStats, Long> {

    @Query("SELECT c.characterStats FROM MyCharacter c WHERE c.id = :characterId")
    Optional<CharacterStats> findCharacterStatsByCharacterId(@Param("characterId") Long characterId);
}
