package dev.zymixon.inventory_service.controllers;

import dev.zymixon.inventory_service.dto.CharacterInventoryDto;
import dev.zymixon.inventory_service.dto.InventorySlotDto;
import dev.zymixon.inventory_service.entities.inventory.CharacterInventory;
//import dev.zymixon.inventory_service.mappers.CharacterInventoryMapper;
import dev.zymixon.inventory_service.entities.inventory.InventorySlot;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.CharacterInventoryMapper;
import dev.zymixon.inventory_service.services.CharacterInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory-service/inventories")
public class CharacterInventoryController {

    private final CharacterInventoryService characterInventoryService;
    private final CharacterInventoryMapper characterInventoryMapper;

    private static final Logger logger = LoggerFactory.getLogger(CharacterEquipmentController.class);


    public CharacterInventoryController(CharacterInventoryService characterInventoryService, CharacterInventoryMapper characterInventoryMapper) {
        this.characterInventoryService = characterInventoryService;
        this.characterInventoryMapper = characterInventoryMapper;
    }

    @PostMapping("/create/{characterId}")
    public ResponseEntity<CharacterInventory> createInventory(@PathVariable Long characterId) {

        //ToDo zabezpieczyc tworzenie wiecej niz 1 inventory na character (wywala błąd)
        CharacterInventory newCharacterInventory = characterInventoryService.createCharacterInventory(characterId);

        return ResponseEntity.ok(newCharacterInventory);
    }

    @GetMapping("/get-inventory/{characterId}")
    public ResponseEntity<CharacterInventoryDto> getInventory(@PathVariable Long characterId) {

        CharacterInventory characterInventory = characterInventoryService.getCharacterInventory(characterId);
        System.out.println("INVENTORY: ");
        for (InventorySlot slot: characterInventory.getInventorySlots()) {
            System.out.println(slot);
        }
        CharacterInventoryDto mapped = characterInventoryMapper.mapToDto(characterInventory);
        System.out.println("MAPPED INVENTORY: ");
        for (InventorySlotDto slotDto: mapped.getInventorySlots()) {
            System.out.println(slotDto);
        }
        return ResponseEntity.ok(characterInventoryMapper.mapToDto(characterInventory));
    }

    //ToDO metoda do zmiany opisane w serwisie
//    @PostMapping("/add-item/{inventoryId}/{position}/{itemTemplateId}/{itemType}")
//    public ResponseEntity<Void> addItemToInventory(@PathVariable Long inventoryId, @PathVariable int position, @PathVariable Long itemTemplateId, @PathVariable String itemType) {
//
//
//        characterInventoryService.addItemToInventoryByPosition(inventoryId, position, itemTemplateId, itemType);
//        return ResponseEntity.ok().build();
//    }
//
    @PutMapping("/move-item/{previousPosition}/{newPosition}/{inventoryId}")
    public ResponseEntity<Void> swapItemsInInventory(@PathVariable int previousPosition, @PathVariable int newPosition, @PathVariable Long inventoryId) {
        logger.info("/inventory-service/inventories/move-item/{}/{}/{}", previousPosition, newPosition, inventoryId);


        characterInventoryService.moveItem(previousPosition, newPosition, inventoryId);

        return ResponseEntity.ok().build();

    }


}
