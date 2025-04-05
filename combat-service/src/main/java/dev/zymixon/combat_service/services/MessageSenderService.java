package dev.zymixon.combat_service.services;

import dev.zymixon.combat_service.models.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MessageSenderService {

    private final AmqpTemplate amqpTemplate;


    private final RabbitTemplate rabbitTemplate;

    public MessageSenderService(AmqpTemplate amqpTemplate, RabbitTemplate rabbitTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    //ToDO zamienilem te 2 metody na multu thread zeby sie jedoczesnie wykonywaly
//    public void sendUpdatedCharacter(MyCharacter character) {
//
//        //create dto object
//        CombatResultUpdate combatResult = CombatResultUpdate.builder()
//                .characterId(character.getId())
//                .experience(character.getExperience())
//                .level(character.getLevel())
//                .characterStatsId(character.getCharacterStats().getId())
//                .currentHealth(character.getCharacterStats().getCurrentHealth())
//                .build();
//
//
//        Object response = amqpTemplate.convertSendAndReceive(
//                "characterExchange", "characterRoutingKey", combatResult);
//
//
//    }

//    public List<Long> sendItemDropGenerateRequest(CombatRequestDto combatRequest) {
//
//        ItemDropGenerateRequest itemDropGenerateRequest = ItemDropGenerateRequest.builder()
//                .enemyLevel(combatRequest.getEnemyInstance().getLevel())
//                .build();
//
////        List<Long> droppedItemIds = amqpTemplate.convertSendAndReceive(
////                "combatRequestExchange", "combatRequestRoutingKey", itemDropGenerateRequest);
//
//
//        return rabbitTemplate.convertSendAndReceiveAsType(
//                "combatRequestExchange",
//                "combatRequestRoutingKey",
//                itemDropGenerateRequest,
//                new ParameterizedTypeReference<List<Long>>() {} // Explicit type declaration
//                 );
//
//    }

    @Async
    public CompletableFuture<List<Long>> sendItemDropGenerateRequest(CombatRequestDto combatRequest) {
        ItemDropGenerateRequest itemDropGenerateRequest = ItemDropGenerateRequest.builder()
                .enemyLevel(combatRequest.getEnemyInstance().getLevel())
                .freeInventorySlotsIds(combatRequest.getPlayerCharacter().getInventory().getInventorySlots().stream().map(InventorySlot::getId).collect(Collectors.toList()))
                .build();

        List<Long> droppedItemIds = rabbitTemplate.convertSendAndReceiveAsType(
                "combatRequestExchange",
                "combatRequestRoutingKey",
                itemDropGenerateRequest,
                new ParameterizedTypeReference<>() {}
        );

        return CompletableFuture.completedFuture(droppedItemIds);
    }

    @Async
    public CompletableFuture<Void> sendUpdatedCharacter(MyCharacter character) {
        CombatResultUpdate combatResult = CombatResultUpdate.builder()
                .characterId(character.getId())
                .experience(character.getExperience())
                .level(character.getLevel())
                .characterStatsId(character.getCharacterStats().getId())
                .currentHealth(character.getCharacterStats().getCurrentHealth())
                .build();

        rabbitTemplate.convertSendAndReceive("characterExchange", "characterRoutingKey", combatResult);
        return CompletableFuture.completedFuture(null);
    }

}
