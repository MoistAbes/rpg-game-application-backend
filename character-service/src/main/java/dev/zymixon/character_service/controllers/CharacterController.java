package dev.zymixon.character_service.controllers;

import dev.zymixon.character_service.repositories.CharacterRepository;
import dev.zymixon.character_service.entities.MyCharacter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterRepository characterRepository;

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);


    public CharacterController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/get-all")
    public List<MyCharacter> getAllCharacters() {
        logger.info("/characters/get-all");
        return characterRepository.findAll();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        logger.info("/characters/test");
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping
    public MyCharacter createCharacter(@RequestBody MyCharacter character) {
        return characterRepository.save(character);
    }

}
