package dev.zymixon.character_service.repositories;

import dev.zymixon.character_service.entities.MyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<MyCharacter, Long> {

    @Query("SELECT c from MyCharacter c where c.userId = :userId")
    List<MyCharacter> findAllByUserId(@Param("userId") Long userId);

}
