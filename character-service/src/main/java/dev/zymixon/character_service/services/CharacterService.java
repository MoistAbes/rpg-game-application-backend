package dev.zymixon.character_service.services;

import dev.zymixon.character_service.entities.MyCharacter;
import dev.zymixon.character_service.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public MyCharacter createCharacter(String name, Long userId) {
        //ToDo
        //dodac zabezpieczenie zeby nie można było utworzyc 2 postaci o tych samych nazwach
        return characterRepository.save(CharacterCreatorUtil.generateCharacter(name, userId));
    }

    public List<MyCharacter> getCharactersByUserId(Long userId) {
        return characterRepository.findAllByUserId(userId);
    }
}
