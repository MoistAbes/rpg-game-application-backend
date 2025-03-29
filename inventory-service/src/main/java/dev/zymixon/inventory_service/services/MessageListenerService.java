package dev.zymixon.inventory_service.services;

import dev.zymixon.inventory_service.models.ItemDropGenerateRequest;
import dev.zymixon.inventory_service.services.item_generation.ItemGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListenerService {

    private final ItemGenerationService itemGenerationService;


    private static final Logger logger = LoggerFactory.getLogger(MessageListenerService.class);

    public MessageListenerService(ItemGenerationService itemGenerationService) {
        this.itemGenerationService = itemGenerationService;
    }


    @RabbitListener(queues = "combatRequestQueue")
    public boolean  handleItemDropGeneration(ItemDropGenerateRequest itemDropGenerateRequest) {
        logger.info("Received item drop generation reqeust: {}", itemDropGenerateRequest);
        // Handle the helmet change message
        return itemGenerationService.generateCombatItemDrops(itemDropGenerateRequest);



    }



}
