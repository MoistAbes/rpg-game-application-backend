package dev.zymixon.character_service.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

    private final RabbitTemplate rabbitTemplate;

    public MessageSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendInventoryEquipmentCreateRequest(Long characterId) {
        rabbitTemplate.convertSendAndReceive("createNewInventoryEquipmentExchange", "createNewInventoryEquipmentRoutingKey", characterId);
    }


}

