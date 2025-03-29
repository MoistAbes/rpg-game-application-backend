package dev.zymixon.character_service.services;

import dev.zymixon.character_service.dto.EquipmentChangeDto;
import dev.zymixon.character_service.dto.EquipmentWeaponChangeDto;
import dev.zymixon.character_service.entities.MyCharacter;
import dev.zymixon.character_service.model.CombatResultUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListenerService {

    private final CharacterService characterService;
    private final LevelExperienceService levelExperienceService;
    private final AmqpTemplate amqpTemplate;
    private final CharacterStatsService characterStatsService;


    public MessageListenerService(CharacterService characterService, LevelExperienceService levelExperienceService, AmqpTemplate amqpTemplate, CharacterStatsService characterStatsService) {
        this.characterService = characterService;
        this.levelExperienceService = levelExperienceService;
        this.amqpTemplate = amqpTemplate;
        this.characterStatsService = characterStatsService;
    }

    private static final Logger logger = LoggerFactory.getLogger(MessageListenerService.class);



    // Listener for helmet equipment change messages
    @RabbitListener(queues = "helmetQueue")
    public boolean  handleHelmetEquipmentChange(EquipmentChangeDto equipmentChangeDto) {
        logger.info("Received helmet change message: {}", equipmentChangeDto);
        // Handle the helmet change message

        return characterService.calculateCharacterStats(equipmentChangeDto);
    }

    // Listener for weapon equipment change messages
    @RabbitListener(queues = "chestQueue")
    public boolean handleChestEquipmentChange(EquipmentChangeDto equipmentChangeDto) {
        logger.info("Received chest change message: {}", equipmentChangeDto);
        // Handle the weapon change message
        return characterService.calculateCharacterStats(equipmentChangeDto);

    }

    // Listener for weapon equipment change messages
    @RabbitListener(queues = "glovesQueue")
    public boolean handleGlovesEquipmentChange(EquipmentChangeDto equipmentChangeDto, Message message) {
        logger.info("Received gloves change message: {}", equipmentChangeDto);
        // Handle the weapon change message
        return characterService.calculateCharacterStats(equipmentChangeDto);

    }

    // Listener for weapon equipment change messages
    @RabbitListener(queues = "bootsQueue")
    public boolean handleBootsEquipmentChange(EquipmentChangeDto equipmentChangeDto) {
        logger.info("Received boots change message: {}", equipmentChangeDto);
        // Handle the weapon change message
        return characterService.calculateCharacterStats(equipmentChangeDto);

    }

    @RabbitListener(queues = "weaponQueue")
    public boolean handleWeaponEquipmentChange(EquipmentWeaponChangeDto equipmentWeaponChangeDto) {
        logger.info("Received weapon change message: {}", equipmentWeaponChangeDto);
        // Handle the weapon change message
        return characterService.calculateCharacterWeaponStats(equipmentWeaponChangeDto);

    }

    //COMBAT SERVICE
    @RabbitListener(queues = "characterQueue")
    public boolean handleCharacterUpdate(CombatResultUpdate combatResultUpdate) {

        //check if level up
        while (levelExperienceService.isLevelUp(combatResultUpdate.getLevel() + 1, combatResultUpdate.getExperience())) {

            combatResultUpdate.setLevel(combatResultUpdate.getLevel() + 1);
            combatResultUpdate.setExperience((int) (combatResultUpdate.getExperience()  - levelExperienceService.getExperienceForLevel(combatResultUpdate.getLevel())));


            //ToDO zrobic jakis konkretny system levelowania poki co nudne dodawanie statystyk tylko jest
            //add max health and bonus armor and bonus attack
            characterStatsService.handleLevelUp(combatResultUpdate.getCharacterStatsId());

        }


        //save updated character level and experience
        characterService.updateCharacterLevelAndExperience(combatResultUpdate.getCharacterId(), combatResultUpdate.getExperience(), combatResultUpdate.getLevel());
        //update character current health
        characterStatsService.updateCharacterCurrentHealth(combatResultUpdate.getCharacterStatsId(), combatResultUpdate.getCurrentHealth());


        return true;
    }
}
