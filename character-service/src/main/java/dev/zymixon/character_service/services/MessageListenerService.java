package dev.zymixon.character_service.services;

import dev.zymixon.character_service.dto.EquipmentChangeDto;
import dev.zymixon.character_service.dto.EquipmentWeaponChangeDto;
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
    private final AmqpTemplate amqpTemplate;


    public MessageListenerService(CharacterService characterService, AmqpTemplate amqpTemplate) {
        this.characterService = characterService;
        this.amqpTemplate = amqpTemplate;
    }

    private static final Logger logger = LoggerFactory.getLogger(MessageListenerService.class);



    // Listener for helmet equipment change messages
    @RabbitListener(queues = "helmetQueue")
    public void handleHelmetEquipmentChange(EquipmentChangeDto equipmentChangeDto) {
        logger.info("Received helmet change message: {}", equipmentChangeDto);
        // Handle the helmet change message

        characterService.calculateCharacterStats(equipmentChangeDto);

    }

    // Listener for weapon equipment change messages
    @RabbitListener(queues = "chestQueue")
    public void handleChestEquipmentChange(EquipmentChangeDto equipmentChangeDto) {
        logger.info("Received chest change message: {}", equipmentChangeDto);
        // Handle the weapon change message
        characterService.calculateCharacterStats(equipmentChangeDto);

    }

    // Listener for weapon equipment change messages
    @RabbitListener(queues = "glovesQueue")
    public void handleGlovesEquipmentChange(EquipmentChangeDto equipmentChangeDto, Message message) {
        logger.info("Received gloves change message: {}", equipmentChangeDto);
        // Handle the weapon change message
        boolean resultStatus = characterService.calculateCharacterStats(equipmentChangeDto);

        //send message that updating is done
        System.out.println("listener status before send back reponse: " + resultStatus);
        if (resultStatus) {
            sendResponse(message, true);
        }
    }

    // Listener for weapon equipment change messages
    @RabbitListener(queues = "bootsQueue")
    public void handleBootsEquipmentChange(EquipmentChangeDto equipmentChangeDto) {
        logger.info("Received boots change message: {}", equipmentChangeDto);
        // Handle the weapon change message
        characterService.calculateCharacterStats(equipmentChangeDto);

    }

    @RabbitListener(queues = "weaponQueue")
    public void handleWeaponEquipmentChange(EquipmentWeaponChangeDto equipmentWeaponChangeDto) {
        logger.info("Received weapon change message: {}", equipmentWeaponChangeDto);
        // Handle the weapon change message
        characterService.calculateCharacterWeaponStats(equipmentWeaponChangeDto);

    }


    private void sendResponse(Message requestMessage, boolean success) {
        String replyToQueue = requestMessage.getMessageProperties().getReplyTo();
        String correlationId = requestMessage.getMessageProperties().getCorrelationId();

        // Set up message properties and send response (boolean as a simple response)
        MessageProperties responseProperties = new MessageProperties();
        responseProperties.setReplyTo(replyToQueue);
        responseProperties.setCorrelationId(correlationId);

        // Serialize the boolean value to a message
        Message responseMessage = new Message(String.valueOf(success).getBytes(), responseProperties);

        // Send the response message to the reply-to queue
        amqpTemplate.send(replyToQueue, responseMessage);
    }
}
