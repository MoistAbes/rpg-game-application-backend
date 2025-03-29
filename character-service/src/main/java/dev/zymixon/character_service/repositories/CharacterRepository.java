package dev.zymixon.character_service.repositories;

import dev.zymixon.character_service.entities.MyCharacter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<MyCharacter, Long> {

    @Query("SELECT c from MyCharacter c where c.userId = :userId")
    List<MyCharacter> findAllByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE MyCharacter c SET c.experience = :experience, c.level = :level WHERE c.id = :characterId")
    void updateCharacterLevelAndExperience(@Param("characterId") Long characterId,
                              @Param("experience") int experience,
                              @Param("level") int level);

}
