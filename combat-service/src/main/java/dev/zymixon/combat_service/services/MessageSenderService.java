package dev.zymixon.combat_service.services;

import dev.zymixon.combat_service.models.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

    private final AmqpTemplate amqpTemplate;

    public MessageSenderService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendUpdatedCharacter(MyCharacter character) {

        //create dto object
        CombatResultUpdate combatResult = CombatResultUpdate.builder()
                .characterId(character.getId())
                .experience(character.getExperience())
                .level(character.getLevel())
                .characterStatsId(character.getCharacterStats().getId())
                .currentHealth(character.getCharacterStats().getCurrentHealth())
                .build();


        Object response = amqpTemplate.convertSendAndReceive(
                "characterExchange", "characterRoutingKey", combatResult);


    }

    public void sendItemDropGenerateRequest(CombatRequestDto combatRequest) {

        ItemDropGenerateRequest itemDropGenerateRequest = ItemDropGenerateRequest.builder()
                .enemyLevel(combatRequest.getEnemyInstance().getLevel())
                .build();

        amqpTemplate.convertSendAndReceive(
                "combatRequestExchange", "combatRequestRoutingKey", itemDropGenerateRequest);

    }

}
