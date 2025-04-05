package dev.zymixon.character_service.controllers;

import dev.zymixon.character_service.dto.MyCharacterDto;
import dev.zymixon.character_service.mappers.MyCharacterMapper;
import dev.zymixon.character_service.repositories.CharacterRepository;
import dev.zymixon.character_service.entities.MyCharacter;
import dev.zymixon.character_service.services.CharacterService;
import dev.zymixon.character_service.services.LevelExperienceService;
import dev.zymixon.character_service.services.MessageSenderService;
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
    private final MessageSenderService messageSenderService;

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    public CharacterController(CharacterService characterService, MyCharacterMapper myCharacterMapper, LevelExperienceService levelExperienceService, MessageSenderService messageSenderService) {
        this.characterService = characterService;
        this.myCharacterMapper = myCharacterMapper;
        this.levelExperienceService = levelExperienceService;
        this.messageSenderService = messageSenderService;
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

        System.out.println("my character: " + myCharacter);

        // Map entity to DTO
        MyCharacterDto characterDTO = myCharacterMapper.toDto(myCharacter);

        System.out.println("mapped character: " + characterDTO);

        // Fetch experience needed for next level
        Long nextLevelExp = levelExperienceService.getExperienceForLevel(myCharacter.getLevel() + 1);
        characterDTO.setNextLevelExperience(nextLevelExp);


        return ResponseEntity.ok(characterDTO);
    }



    @PostMapping("/create/{name}/{userId}")
    public Long createCharacter(@PathVariable String name, @PathVariable Long userId) {
        Long characterId = characterService.createCharacter(name, userId);
        messageSenderService.sendInventoryEquipmentCreateRequest(characterId);
        return characterId;
    }

}
