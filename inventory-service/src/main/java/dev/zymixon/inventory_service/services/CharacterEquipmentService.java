package dev.zymixon.inventory_service.services;

import dev.zymixon.inventory_service.entities.equipment.CharacterEquipment;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.inventory.InventorySlot;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.exceptions.CharacterEquipmentNotFoundException;
import dev.zymixon.inventory_service.exceptions.InventorySlotNotFoundException;
import dev.zymixon.inventory_service.repositories.CharacterEquipmentRepository;
import dev.zymixon.inventory_service.repositories.InventorySlotRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterEquipmentService {

    private final CharacterEquipmentRepository characterEquipmentRepository;
    private final InventorySlotRepository inventorySlotRepository;
    private final MessageSenderService messageSenderService;


    public CharacterEquipmentService(CharacterEquipmentRepository characterEquipmentRepository, InventorySlotRepository inventorySlotRepository, MessageSenderService messageSenderService) {
        this.characterEquipmentRepository = characterEquipmentRepository;
        this.inventorySlotRepository = inventorySlotRepository;
        this.messageSenderService = messageSenderService;
    }


    public CharacterEquipment getCharacterEquipment(Long characterId) {
        return characterEquipmentRepository.findByCharacterId(characterId)
                .orElseThrow(() -> new CharacterEquipmentNotFoundException("Character equipment belonging to character id: " + characterId + " not found!"));
    }


    public void unequipItemToEmptySlot(Long inventorySlotId, Long equipmentId, ItemType itemType) {

        CharacterEquipment characterEquipment = getCharacterEquipment(equipmentId);
        ItemInstance equipmentItem = getEquipmentItem(itemType, characterEquipment);

        //remove equipped item
        unequipItem(itemType, equipmentId);

        //move equipment item to inventory
        inventorySlotRepository.updateInventoryItemBySlotId(equipmentItem.getId(), inventorySlotId);

        //send message to character about unequipping item
        messageSenderService.sendEquipmentChangeInformation(equipmentItem, null, itemType, characterEquipment.getCharacterId());

    }

    public void unequipItemToTakenSlot(Long inventorySlotId, Long equipmentId, ItemType itemType) {

        InventorySlot inventorySlot = inventorySlotRepository.findById(inventorySlotId).orElseThrow(() -> new InventorySlotNotFoundException("Inventory slot not found!"));
        CharacterEquipment characterEquipment = getCharacterEquipment(equipmentId);

        ItemInstance equipmentItem = getEquipmentItem(itemType, characterEquipment);


        //null inventory item
        inventorySlotRepository.clearInventorySlotItem(inventorySlotId);

        //equip item from inventory
        equipItem(inventorySlot.getItemInstance(), itemType, equipmentId);
//        characterEquipmentRepository.updateCharacterEquipmentHelmet(inventorySlot.getItemInstance(), equipmentId);


        //add to iventory equipment item
        inventorySlotRepository.updateInventoryItemBySlotId(equipmentItem.getId(), inventorySlot.getId());


        messageSenderService.sendEquipmentChangeInformation(equipmentItem, inventorySlot.getItemInstance(), itemType, characterEquipment.getCharacterId());


    }

    public void equipItemToEmptySlot(Long inventorySlotId, Long equipmentId, ItemType itemType) {


        InventorySlot inventorySlot = inventorySlotRepository.findById(inventorySlotId).orElseThrow(() -> new InventorySlotNotFoundException("Inventory slot not found!"));
        CharacterEquipment characterEquipment = getCharacterEquipment(equipmentId);

        //null inventory item
        inventorySlotRepository.clearInventorySlotItem(inventorySlotId);

        equipItem(inventorySlot.getItemInstance(), itemType, equipmentId);

        //send message to character about equipping item
        messageSenderService.sendEquipmentChangeInformation(null, inventorySlot.getItemInstance(), itemType, characterEquipment.getCharacterId());


    }



    public void equipItemToTakenSlot(Long inventorySlotId, Long equipmentId, ItemType itemType) {

        InventorySlot inventorySlot = inventorySlotRepository.findById(inventorySlotId).orElseThrow(() -> new InventorySlotNotFoundException("Inventory slot not found!"));
        CharacterEquipment characterEquipment = getCharacterEquipment(equipmentId);

        //null inventory item
        inventorySlotRepository.clearInventorySlotItem(inventorySlotId);

        ItemInstance equipmentItem = getEquipmentItem(itemType, characterEquipment);


        equipItem(inventorySlot.getItemInstance(), itemType, equipmentId);

        inventorySlotRepository.updateInventoryItemBySlotId(equipmentItem.getId(), inventorySlot.getId());

        messageSenderService.sendEquipmentChangeInformation(equipmentItem, inventorySlot.getItemInstance(), itemType, characterEquipment.getCharacterId());



    }

    private void equipItem(ItemInstance itemInstance, ItemType itemType, Long equipmentId) {

        switch (itemType) {
            case HELMET_ITEM_INSTANCE -> characterEquipmentRepository.updateCharacterEquipmentHelmet(itemInstance, equipmentId);
            case CHEST_ITEM_INSTANCE -> characterEquipmentRepository.updateCharacterEquipmentChest(itemInstance, equipmentId);
            case GLOVES_ITEM_INSTANCE -> characterEquipmentRepository.updateCharacterEquipmentGloves(itemInstance, equipmentId);
            case BOOTS_ITEM_INSTANCE -> characterEquipmentRepository.updateCharacterEquipmentBoots(itemInstance, equipmentId);

            case WEAPON_ITEM_INSTANCE ->     characterEquipmentRepository.updateCharacterEquipmentMainHand(itemInstance, equipmentId);
        }

    }

    private void unequipItem(ItemType itemType, Long equipmentId) {

        switch (itemType) {
            case HELMET_ITEM_INSTANCE -> characterEquipmentRepository.updateCharacterEquipmentHelmet(null, equipmentId);
            case CHEST_ITEM_INSTANCE -> characterEquipmentRepository.updateCharacterEquipmentChest(null, equipmentId);
            case GLOVES_ITEM_INSTANCE -> characterEquipmentRepository.updateCharacterEquipmentGloves(null, equipmentId);
            case BOOTS_ITEM_INSTANCE -> characterEquipmentRepository.updateCharacterEquipmentBoots(null, equipmentId);

            case WEAPON_ITEM_INSTANCE -> characterEquipmentRepository.updateCharacterEquipmentMainHand(null, equipmentId);

        }

    }

    private ItemInstance getEquipmentItem(ItemType itemType, CharacterEquipment characterEquipment) {

        System.out.println("GET EQUIPMENT ITEM: " + itemType);

        switch (itemType) {
            case BOOTS_ITEM_INSTANCE -> {
                return characterEquipment.getBoots();
            }
            case CHEST_ITEM_INSTANCE -> {
                return characterEquipment.getChest();
            }
            case HELMET_ITEM_INSTANCE -> {
                return characterEquipment.getHelmet();
            }
            case GLOVES_ITEM_INSTANCE -> {
                return characterEquipment.getGloves();
            }
            case WEAPON_ITEM_INSTANCE -> {
                return characterEquipment.getMainHand();
            }
            default -> throw new InventorySlotNotFoundException("Inventory slot not found!");
        }
    }
}
