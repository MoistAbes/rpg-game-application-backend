package dev.zymixon.character_service.repositories;

import dev.zymixon.character_service.entities.LevelExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelExperienceRepository extends JpaRepository<LevelExperience, Long> {
}
