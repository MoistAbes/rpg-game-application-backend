package dev.zymixon.character_service.controllers;

import dev.zymixon.character_service.entities.CharacterStats;
import dev.zymixon.character_service.services.CharacterStatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters/stats")
public class CharacterStatsController {

    private final CharacterStatsService characterStatsService;
    private static final Logger logger = LoggerFactory.getLogger(CharacterStatsController.class);


    public CharacterStatsController(CharacterStatsService characterStatsService) {
        this.characterStatsService = characterStatsService;
    }

    @GetMapping("/get-by-character-id/{characterId}")
    public ResponseEntity<CharacterStats> getCharacterStatsByCharacterId(@PathVariable Long characterId) {

        CharacterStats characterStats = characterStatsService.getCharacterStatsByCharacterId(characterId);

        return ResponseEntity.ok(characterStats);
    }

    @PutMapping("/current-health/{characterStatsId}/{currentHealth}")
    public ResponseEntity<Void> healCharacter(@PathVariable("characterStatsId") Long characterStatsId,@PathVariable Integer currentHealth) {
        logger.info("/characters/stats/current-health/{}/{} ", characterStatsId, currentHealth);


        characterStatsService.updateCharacterCurrentHealth(characterStatsId, currentHealth);

        return ResponseEntity.ok().build();

    }
}
