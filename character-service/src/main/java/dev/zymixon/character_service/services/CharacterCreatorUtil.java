package dev.zymixon.character_service.services;

import dev.zymixon.character_service.entities.MyCharacter;

public class CharacterCreatorUtil {

    public static MyCharacter generateCharacter(String characterName, Long userId) {
        MyCharacter character = MyCharacter.builder()
                .name(characterName)
                .level(1)
                .experience(0)
                .attack(5)
                .defense(0)
                .maxHealth(100)
                .currentHealth(100)
                .userId(userId)
                .build();

        return character;
    }

}
