package dev.zymixon.inventory_service.services;

import dev.zymixon.inventory_service.dto.EquipmentChangeDto;
import dev.zymixon.inventory_service.dto.EquipmentWeaponChangeDto;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.instance.impl.*;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.ItemStatMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageSenderService {

    private final AmqpTemplate amqpTemplate;
    private final ItemStatMapper itemStatMapper;

    public MessageSenderService(AmqpTemplate amqpTemplate, ItemStatMapper itemStatMapper) {
        this.amqpTemplate = amqpTemplate;
        this.itemStatMapper = itemStatMapper;
    }


    public void sendEquipmentChangeInformation(ItemInstance prevEquippedItem, ItemInstance newEquippedItem, ItemType itemType, Long characterId) {
        System.out.println("sending message...");
        System.out.println("prevItem: " + prevEquippedItem);
        System.out.println("newItem: " + newEquippedItem);

        // Switch based on item type
        switch (itemType) {
            case HELMET_ITEM_INSTANCE:
                System.out.println("helmet sending");
                if (prevEquippedItem != null && newEquippedItem != null) {
                    //swap
                    sendHelmetEquipmentChangeInformation((HelmetItemInstance) prevEquippedItem, (HelmetItemInstance) newEquippedItem, characterId);
                } else if (newEquippedItem == null) {
                    //unequip
                    sendHelmetEquipmentChangeInformation((HelmetItemInstance) prevEquippedItem, null, characterId); // For unequip
                } else {
                    //equip
                    sendHelmetEquipmentChangeInformation(null, (HelmetItemInstance) newEquippedItem, characterId);
                }
                break;
            case CHEST_ITEM_INSTANCE:
                if (prevEquippedItem != null && newEquippedItem != null) {
                    sendChestEquipmentChangeInformation((ChestItemInstance) prevEquippedItem, (ChestItemInstance) newEquippedItem, characterId);
                } else if (newEquippedItem == null) {
                    sendChestEquipmentChangeInformation((ChestItemInstance) prevEquippedItem, null, characterId); // For unequip
                }else {
                    //equip
                    sendChestEquipmentChangeInformation(null, (ChestItemInstance) newEquippedItem, characterId);
                }
                break;
            case GLOVES_ITEM_INSTANCE:
                if (prevEquippedItem != null && newEquippedItem != null) {
                    sendGlovesEquipmentChangeInformation((GlovesItemInstance) prevEquippedItem, (GlovesItemInstance) newEquippedItem, characterId);
                } else if (newEquippedItem == null) {
                    sendGlovesEquipmentChangeInformation((GlovesItemInstance) prevEquippedItem, null, characterId); // For unequip
                }else {
                    //equip
                    sendGlovesEquipmentChangeInformation(null, (GlovesItemInstance) newEquippedItem, characterId);
                }
                break;
            case BOOTS_ITEM_INSTANCE:
                if (prevEquippedItem != null && newEquippedItem != null) {
                    sendBootsEquipmentChangeInformation((BootsItemInstance) prevEquippedItem, (BootsItemInstance) newEquippedItem, characterId);
                } else if (newEquippedItem == null) {
                    sendBootsEquipmentChangeInformation((BootsItemInstance) prevEquippedItem, null, characterId); // For unequip
                }else {
                    //equip
                    sendBootsEquipmentChangeInformation(null, (BootsItemInstance) newEquippedItem, characterId);
                }
                break;
            case WEAPON_ITEM_INSTANCE:
                if (prevEquippedItem != null && newEquippedItem != null) {
                    sendWeaponEquipmentChangeInformation((WeaponItemInstance) prevEquippedItem, (WeaponItemInstance) newEquippedItem, characterId);
                } else if (newEquippedItem == null) {
                    sendWeaponEquipmentChangeInformation((WeaponItemInstance) prevEquippedItem, null, characterId); // For unequip
                }else {
                    //equip
                    sendWeaponEquipmentChangeInformation(null, (WeaponItemInstance) newEquippedItem, characterId);
                }
        }


    }


    private void sendHelmetEquipmentChangeInformation(HelmetItemInstance prevEquippedItem, HelmetItemInstance newEquippedItem, Long characterId) {


        EquipmentChangeDto equipmentChangeDto;

        //unequip item
        if (newEquippedItem == null){
            System.out.println("Helmet changed: " + prevEquippedItem.getHelmetTemplate().getName() + " -> " + "empty");
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .prevArmorValue(prevEquippedItem.getArmorValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
                    .build();
            //equip item
        }else if (prevEquippedItem == null){
            System.out.println("helmet changed: " + "empty" + " -> " + newEquippedItem.getHelmetTemplate().getName());
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .newArmorValue(newEquippedItem.getArmorValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        }else {
            //swap items
            System.out.println("helmet swapping: " + prevEquippedItem.getHelmetTemplate().getName() + " -> " + newEquippedItem.getHelmetTemplate().getName());
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .prevArmorValue(prevEquippedItem.getArmorValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
                    .newArmorValue(newEquippedItem.getArmorValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        }
//        amqpTemplate.convertAndSend("exampleExchange", "helmetRoutingKey", equipmentChangeDto);
        Object response = amqpTemplate.convertSendAndReceive(
                "exampleExchange", "helmetRoutingKey", equipmentChangeDto);

        if (response != null) {
            System.out.println("Received response: " + response);
            // Continue with your sender method logic now that response is received
        } else {
            System.out.println("No response received");
        }
    }

    private void sendChestEquipmentChangeInformation(ChestItemInstance prevEquippedItem, ChestItemInstance newEquippedItem, Long characterId) {
        EquipmentChangeDto equipmentChangeDto;

        //unequip item
        if (newEquippedItem == null) {
            System.out.println("Chest changed: " + prevEquippedItem.getChestTemplate().getName() + " -> " + "empty");
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .prevArmorValue(prevEquippedItem.getArmorValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
                    .build();

            //equip item
        } else if (prevEquippedItem == null) {
            System.out.println("Chest changed: " + "empty" + " -> " + newEquippedItem.getChestTemplate().getName());
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .newArmorValue(newEquippedItem.getArmorValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        } else {
            //swap items
            System.out.println("Chest swapping: " + prevEquippedItem.getChestTemplate().getName() + " -> " + newEquippedItem.getChestTemplate().getName());
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .prevArmorValue(prevEquippedItem.getArmorValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
                    .newArmorValue(newEquippedItem.getArmorValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        }
//        amqpTemplate.convertAndSend("exampleExchange", "chestRoutingKey", equipmentChangeDto);

        Object response = amqpTemplate.convertSendAndReceive(
                "exampleExchange", "chestRoutingKey", equipmentChangeDto);

        if (response != null) {
            System.out.println("Received response: " + response);
            // Continue with your sender method logic now that response is received
        } else {
            System.out.println("No response received");
        }
    }

    private void sendGlovesEquipmentChangeInformation(GlovesItemInstance prevEquippedItem, GlovesItemInstance newEquippedItem, Long characterId) {
        EquipmentChangeDto equipmentChangeDto;

        //unequip item
        if (newEquippedItem == null){
            System.out.println("Gloves changed: " + prevEquippedItem.getGlovesTemplate().getName() + " -> " + "empty");
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .prevArmorValue(prevEquippedItem.getArmorValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
                    .build();

            //equip item
        }else if (prevEquippedItem == null){
            System.out.println("Gloves changed: " + "empty" + " -> " + newEquippedItem.getGlovesTemplate().getName());
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .newArmorValue(newEquippedItem.getArmorValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        }else {
            //swap items
            System.out.println("Gloves swapping: " + prevEquippedItem.getGlovesTemplate().getName() + " -> " + newEquippedItem.getGlovesTemplate().getName());
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .prevArmorValue(prevEquippedItem.getArmorValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
                    .newArmorValue(newEquippedItem.getArmorValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        }

        //orginalna wersja
//        amqpTemplate.convertAndSend("exampleExchange", "glovesRoutingKey", equipmentChangeDto);

        Object response = amqpTemplate.convertSendAndReceive(
                "exampleExchange", "glovesRoutingKey", equipmentChangeDto);

        if (response != null) {
            System.out.println("Received response: " + response);
            // Continue with your sender method logic now that response is received
        } else {
            System.out.println("No response received");
        }
    }

    private void sendBootsEquipmentChangeInformation(BootsItemInstance prevEquippedItem, BootsItemInstance newEquippedItem, Long characterId) {
        EquipmentChangeDto equipmentChangeDto;

        //unequip item
        if (newEquippedItem == null){
            System.out.println("Boots changed: " + prevEquippedItem.getBootsTemplate().getName() + " -> " + "empty");
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .prevArmorValue(prevEquippedItem.getArmorValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
                    .build();

            //equip item
        }else if (prevEquippedItem == null){
            System.out.println("Boots changed: " + "empty" + " -> " + newEquippedItem.getBootsTemplate().getName());
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .newArmorValue(newEquippedItem.getArmorValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        }else {
            //swap items
            System.out.println("Boots swapping: " + prevEquippedItem.getBootsTemplate().getName() + " -> " + newEquippedItem.getBootsTemplate().getName());
            equipmentChangeDto = EquipmentChangeDto.builder()
                    .characterId(characterId)
                    .prevArmorValue(prevEquippedItem.getArmorValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
                    .newArmorValue(newEquippedItem.getArmorValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        }


//        amqpTemplate.convertAndSend("exampleExchange", "bootsRoutingKey", equipmentChangeDto);

        Object response = amqpTemplate.convertSendAndReceive(
                "exampleExchange", "bootsRoutingKey", equipmentChangeDto);

        if (response != null) {
            System.out.println("Received response: " + response);
            // Continue with your sender method logic now that response is received
        } else {
            System.out.println("No response received");
        }
    }

    private void sendWeaponEquipmentChangeInformation(WeaponItemInstance prevEquippedItem, WeaponItemInstance newEquippedItem, Long characterId) {
        EquipmentWeaponChangeDto equipmentWeaponChangeDto;

        //unequip item
        if (newEquippedItem == null){
            equipmentWeaponChangeDto = EquipmentWeaponChangeDto.builder()
                    .characterId(characterId)
                    .prevDamageValue(prevEquippedItem.getDamageValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
                    .build();

            //equip item
        }else if (prevEquippedItem == null){
//            System.out.println("Boots changed: " + "empty" + " -> " + newEquippedItem.getBootsTemplate().getName());
            equipmentWeaponChangeDto = EquipmentWeaponChangeDto.builder()
                    .characterId(characterId)
                    .newDamageValue(newEquippedItem.getDamageValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        }else {
            //swap items
//            System.out.println("Boots swapping: " + prevEquippedItem.getBootsTemplate().getName() + " -> " + newEquippedItem.getBootsTemplate().getName());
            equipmentWeaponChangeDto = EquipmentWeaponChangeDto.builder()
                    .characterId(characterId)
//                    .prevArmorValue(prevEquippedItem.getArmorValue())
                    .prevDamageValue(prevEquippedItem.getDamageValue())
                    .prevItemStats(itemStatMapper.mapListToDto(prevEquippedItem.getStats()))
//                    .newArmorValue(newEquippedItem.getArmorValue())
                    .newDamageValue(newEquippedItem.getDamageValue())
                    .newItemStats(itemStatMapper.mapListToDto(newEquippedItem.getStats()))
                    .build();
        }


//        amqpTemplate.convertAndSend("exampleExchange", "weaponRoutingKey", equipmentWeaponChangeDto);

        Object response = amqpTemplate.convertSendAndReceive(
                "exampleExchange", "weaponRoutingKey", equipmentWeaponChangeDto);

        if (response != null) {
            System.out.println("Received response: " + response);
            // Continue with your sender method logic now that response is received
        } else {
            System.out.println("No response received");
        }
    }

}
