package dev.zymixon.character_service.repositories;

import dev.zymixon.character_service.entities.CharacterStats;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterStatsRepository extends JpaRepository<CharacterStats, Long> {

    @Query("SELECT c.characterStats FROM MyCharacter c WHERE c.id = :characterId")
    Optional<CharacterStats> findCharacterStatsByCharacterId(@Param("characterId") Long characterId);

    @Transactional
    @Modifying
    @Query("UPDATE CharacterStats cs SET cs.currentHealth = :currentHealth WHERE cs.id = :statsId")
    void updateCharacterCurrentHealth(@Param("statsId") Long statsId,
                               @Param("currentHealth") int currentHealth);


    @Transactional
    @Modifying
    @Query("UPDATE CharacterStats c " +
            "SET c.maxHealth = c.maxHealth + :maxHealthIncrement, " +
            "    c.attack = c.attack + :attackIncrement, " +
            "    c.defense = c.defense + :defenseIncrement " +
            "WHERE c.id = :id")
    void updateCharacterStatsLevelUp(@Param("id") Long id,
                                     @Param("maxHealthIncrement") int maxHealthIncrement,
                                     @Param("attackIncrement") int attackIncrement,
                                     @Param("defenseIncrement") int defenseIncrement);
}
