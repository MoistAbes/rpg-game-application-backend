package dev.zymixon.inventory_service.controllers;


import dev.zymixon.inventory_service.dto.CharacterEquipmentDto;
import dev.zymixon.inventory_service.entities.equipment.CharacterEquipment;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.CharacterEquipmentMapper;
import dev.zymixon.inventory_service.services.CharacterEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory-service/equipments")
public class CharacterEquipmentController {

    private final CharacterEquipmentService characterEquipmentService;
    private final CharacterEquipmentMapper characterEquipmentMapper;



    private static final Logger logger = LoggerFactory.getLogger(CharacterEquipmentController.class);


    public CharacterEquipmentController(CharacterEquipmentService characterEquipmentService, CharacterEquipmentMapper characterEquipmentMapper) {
        this.characterEquipmentService = characterEquipmentService;
        this.characterEquipmentMapper = characterEquipmentMapper;
    }


    @GetMapping("/get-equipment/{characterId}")
    public ResponseEntity<CharacterEquipmentDto> getEquipment(@PathVariable Long characterId) {

        CharacterEquipment characterEquipment = characterEquipmentService.getCharacterEquipment(characterId);


        CharacterEquipmentDto mappedEquipment = characterEquipmentMapper.mapToDto(characterEquipment);
//        System.out.println("MAPPED EQUIPMENT: ");
//
//
//        System.out.println("Helmet: " + mappedEquipment.getHelmet());
//        System.out.println("Chest: " + mappedEquipment.getChest());
//        System.out.println("Gloves: " + mappedEquipment.getGloves());
//        System.out.println("Boots: " + mappedEquipment.getBoots());


        return ResponseEntity.ok(mappedEquipment);
    }

    @PutMapping("/unequip-item-to-empty-slot/{inventorySlotId}/{equipmentId}")
    public ResponseEntity<Void> unequipItemToEmptySlot(@PathVariable Long inventorySlotId, @PathVariable Long equipmentId, @RequestParam ItemType itemType) {
        logger.info("/inventory-service/equipments/unequip-item-to-empty-slot/{}/{}", inventorySlotId, equipmentId);

        characterEquipmentService.unequipItemToEmptySlot(inventorySlotId, equipmentId, itemType);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/unequip-item-to-taken-slot/{inventorySlotId}/{equipmentId}")
    public ResponseEntity<Void> unequipItemToTakenSlot(@PathVariable Long inventorySlotId, @PathVariable Long equipmentId, @RequestParam ItemType itemType) {
        logger.info("/inventory-service/equipments/unequip-item-to-taken-slot/{}/{}", inventorySlotId, equipmentId);

        characterEquipmentService.unequipItemToTakenSlot(inventorySlotId, equipmentId, itemType);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/equip-item-to-empty-slot/{inventorySlotId}/{equipmentId}")
    public ResponseEntity<Void> equipItemToEmptySlot(@PathVariable Long inventorySlotId, @PathVariable Long equipmentId, @RequestParam ItemType itemType) {
        logger.info("/inventory-service/equipments/equip-item-to-empty-slot/{}/{}", inventorySlotId, equipmentId);

        characterEquipmentService.equipItemToEmptySlot(inventorySlotId, equipmentId, itemType);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/equip-item-to-taken-slot/{inventorySlotId}/{equipmentId}")
    public ResponseEntity<Void> equipItemToTakenSlot(@PathVariable Long inventorySlotId, @PathVariable Long equipmentId, @RequestParam ItemType itemType) {
        logger.info("/inventory-service/equipments/equip-item-to-taken-slot/{}/{}", inventorySlotId, equipmentId);

        characterEquipmentService.equipItemToTakenSlot(inventorySlotId, equipmentId, itemType);

        return ResponseEntity.ok().build();
    }

}
