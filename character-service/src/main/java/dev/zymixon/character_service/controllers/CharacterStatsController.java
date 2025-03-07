package dev.zymixon.character_service.controllers;

import dev.zymixon.character_service.entities.CharacterStats;
import dev.zymixon.character_service.services.CharacterStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters/stats")
public class CharacterStatsController {

    private final CharacterStatsService characterStatsService;

    public CharacterStatsController(CharacterStatsService characterStatsService) {
        this.characterStatsService = characterStatsService;
    }

    @GetMapping("/get-by-character-id/{characterId}")
    public ResponseEntity<CharacterStats> getCharacterStatsByCharacterId(@PathVariable Long characterId) {

        CharacterStats characterStats = characterStatsService.getCharacterStatsByCharacterId(characterId);

        return ResponseEntity.ok(characterStats);
    }
}
