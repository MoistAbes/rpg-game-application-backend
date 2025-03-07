package dev.zymixon.character_service.services;

import dev.zymixon.character_service.entities.CharacterStats;
import dev.zymixon.character_service.repositories.CharacterStatsRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterStatsService {

    private final CharacterStatsRepository characterStatsRepository;

    public CharacterStatsService(CharacterStatsRepository characterStatsRepository) {
        this.characterStatsRepository = characterStatsRepository;
    }

    public CharacterStats getCharacterStatsByCharacterId(Long characterId) {
        //ToDO dorobic tutaj exception na to
        return characterStatsRepository.findCharacterStatsByCharacterId(characterId).orElseThrow(() -> new RuntimeException("Character stats not found"));
    }
}
