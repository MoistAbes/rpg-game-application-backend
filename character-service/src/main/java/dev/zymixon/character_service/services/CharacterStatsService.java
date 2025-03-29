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

    public void updateCharacterCurrentHealth(Long characterStatsId, int currentHealth) {
        characterStatsRepository.updateCharacterCurrentHealth(characterStatsId, currentHealth);
    }

    public void handleLevelUp(Long characterStatsId) {

        //ToDO poki co na sztywno dodajemy zycie attack i defense
        int maxHealthIncrement = 10;
        int attackIncrement = 5;
        int defenseIncrement = 5;

        characterStatsRepository.updateCharacterStatsLevelUp(characterStatsId, maxHealthIncrement, attackIncrement, defenseIncrement);

    }
}
