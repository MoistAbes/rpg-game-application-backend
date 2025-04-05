package dev.zymixon.inventory_service.services;

import dev.zymixon.inventory_service.models.ItemDropGenerateRequest;
import dev.zymixon.inventory_service.services.item_generation.ItemGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageListenerService {

    private final ItemGenerationService itemGenerationService;
    private final CharacterInventoryService characterInventoryService;
    private final CharacterEquipmentService characterEquipmentService;


    private static final Logger logger = LoggerFactory.getLogger(MessageListenerService.class);

    public MessageListenerService(ItemGenerationService itemGenerationService, CharacterInventoryService characterInventoryService, CharacterEquipmentService characterEquipmentService) {
        this.itemGenerationService = itemGenerationService;
        this.characterInventoryService = characterInventoryService;
        this.characterEquipmentService = characterEquipmentService;
    }


    @RabbitListener(queues = "combatRequestQueue")
    public List<Long> handleItemDropGeneration(ItemDropGenerateRequest itemDropGenerateRequest) {
        logger.info("Received item drop generation reqeust: {}", itemDropGenerateRequest);
        // Handle the helmet change message
        return itemGenerationService.generateCombatItemDrops(itemDropGenerateRequest);

    }

    @RabbitListener(queues = "createNewInventoryEquipmentQueue")
    public boolean handleCreateNewInventoryEquipment(Long characterId) {
        logger.info("Received create new inventory and equipment reqeust: {}", characterId);
        // Handle the helmet change message

        characterInventoryService.createCharacterInventory(characterId);
        characterEquipmentService.createCharacterEquipment(characterId);

        return true;
    }





}
