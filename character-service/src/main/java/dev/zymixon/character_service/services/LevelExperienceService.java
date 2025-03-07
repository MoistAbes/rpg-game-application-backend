package dev.zymixon.character_service.services;

import dev.zymixon.character_service.entities.LevelExperience;
import dev.zymixon.character_service.repositories.LevelExperienceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LevelExperienceService {

    private final LevelExperienceRepository levelExperienceRepository;

    public LevelExperienceService(LevelExperienceRepository levelExperienceRepository) {
        this.levelExperienceRepository = levelExperienceRepository;
    }

    private final Map<Integer, Long> levelExperienceMap = new HashMap<>();

    @PostConstruct
    public void loadLevelExperience() {
        List<LevelExperience> levels = levelExperienceRepository.findAll();
        for (LevelExperience level : levels) {
            levelExperienceMap.put(level.getLevel(), level.getExperience());
        }
    }

    public Long getExperienceForLevel(int level) {
        return levelExperienceMap.getOrDefault(level, Long.MAX_VALUE); // Prevent errors
    }
}
