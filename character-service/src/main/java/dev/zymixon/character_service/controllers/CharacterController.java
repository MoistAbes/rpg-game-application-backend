package dev.zymixon.character_service.controllers;

import dev.zymixon.character_service.dto.MyCharacterDto;
import dev.zymixon.character_service.mappers.MyCharacterMapper;
import dev.zymixon.character_service.repositories.CharacterRepository;
import dev.zymixon.character_service.entities.MyCharacter;
import dev.zymixon.character_service.services.CharacterService;
import dev.zymixon.character_service.services.LevelExperienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final MyCharacterMapper myCharacterMapper;
    private final LevelExperienceService levelExperienceService;

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    public CharacterController(CharacterService characterService, MyCharacterMapper myCharacterMapper, LevelExperienceService levelExperienceService) {
        this.characterService = characterService;
        this.myCharacterMapper = myCharacterMapper;
        this.levelExperienceService = levelExperienceService;
    }

    @GetMapping("/get-all-by-user/{userId}")
    public List<MyCharacter> getAllByUser(@PathVariable Long userId) {
        logger.info("/characters/get-all-by-user/");

        return characterService.getCharactersByUserId(userId);
    }

    @GetMapping("/get-character/{characterId}")
    public ResponseEntity<MyCharacterDto> getCharacter(@PathVariable Long characterId) {
        logger.info("/characters/get-character/{}/", characterId);
        MyCharacter myCharacter = characterService.getCharacterById(characterId);

        // Map entity to DTO
        MyCharacterDto characterDTO = myCharacterMapper.toDto(myCharacter);

        // Fetch experience needed for next level
        Long nextLevelExp = levelExperienceService.getExperienceForLevel(myCharacter.getLevel() + 1);
        characterDTO.setNextLevelExperience(nextLevelExp);


        return ResponseEntity.ok(characterDTO);
    }


    //orginalna wersja dziala
//    @GetMapping("/get-character/{characterId}")
//    public ResponseEntity<MyCharacter> getCharacter(@PathVariable Long characterId) {
//        logger.info("/characters/get-character/{}/", characterId);
//
//        return ResponseEntity.ok(characterService.getCharacterById(characterId));
//    }

    @PostMapping("/create/{name}/{userId}")
    public MyCharacter createCharacter(@PathVariable String name, @PathVariable Long userId) {
        return characterService.createCharacter(name, userId);
    }

}
