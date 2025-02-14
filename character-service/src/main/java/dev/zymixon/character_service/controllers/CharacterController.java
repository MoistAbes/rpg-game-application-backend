package dev.zymixon.character_service.controllers;

import dev.zymixon.character_service.repositories.CharacterRepository;
import dev.zymixon.character_service.entities.MyCharacter;
import dev.zymixon.character_service.services.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;

    }

    @GetMapping("/get-all-by-user/{userId}")
    public List<MyCharacter> getAllByUser(@PathVariable Long userId) {
        logger.info("/characters/get-all-by-user/");

        return characterService.getCharactersByUserId(userId);
    }

    @PostMapping("/create/{name}/{userId}")
    public MyCharacter createCharacter(@PathVariable String name, @PathVariable Long userId) {
        return characterService.createCharacter(name, userId);
    }

}
