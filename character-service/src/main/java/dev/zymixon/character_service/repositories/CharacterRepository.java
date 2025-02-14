package dev.zymixon.character_service.repositories;

import dev.zymixon.character_service.entities.MyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<MyCharacter, Integer> {
}
