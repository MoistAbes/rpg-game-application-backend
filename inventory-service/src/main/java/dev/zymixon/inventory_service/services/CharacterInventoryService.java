package dev.zymixon.inventory_service.services;

import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.inventory.CharacterInventory;
import dev.zymixon.inventory_service.entities.inventory.InventorySlot;
import dev.zymixon.inventory_service.exceptions.CharacterInventoryNotFoundException;
import dev.zymixon.inventory_service.repositories.CharacterInventoryRepository;
import dev.zymixon.inventory_service.repositories.InventorySlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterInventoryService {

    private final CharacterInventoryRepository characterInventoryRepository;
    private final InventorySlotRepository inventorySlotRepository;

    public CharacterInventoryService(CharacterInventoryRepository characterInventoryRepository, InventorySlotRepository inventorySlotRepository) {
        this.characterInventoryRepository = characterInventoryRepository;
        this.inventorySlotRepository = inventorySlotRepository;
    }


    public CharacterInventory createCharacterInventory(Long characterId) {

        List<InventorySlot> newInventorySlots = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            InventorySlot newInventorySlot = InventorySlot.builder()
                    .position(i)
                    .build();

            newInventorySlots.add(newInventorySlot);
        }

        CharacterInventory newCharacterInventory = CharacterInventory.builder()
                .characterId(characterId)
                .inventorySlots(newInventorySlots)
                .build();

        newInventorySlots.forEach(inventorySlot -> {
            inventorySlot.setInventory(newCharacterInventory);
        });

        return characterInventoryRepository.save(newCharacterInventory);
    }

    public CharacterInventory getCharacterInventory(Long characterId) {


        return characterInventoryRepository.findCharacterInventoryByCharacterId(characterId)
                .orElseThrow(() -> new CharacterInventoryNotFoundException("Character inventory not found with id: " + characterId));
    }

    //ToDO metoda do przerobienia gyd bedziemy dodawac item do eq bedzie juz raczej wygenerowany zostanie jedynie przypisanie go do slota

//    public void addItemToInventoryByPosition(Long inventoryId, int position, Long itemTemplateId, String itemType) {
//
//        ItemTemplate inventoryItemTemplate = null;
//
//        //create inventory item
//        if (itemType.equals("NOT_USABLE")) {
//            inventoryItemTemplate = notUsableItemRepository
//                    .findById(itemTemplateId)
//                    .orElseThrow(() -> new NotUsableItemNotFoundException("NotUsableItem with ID " + itemTemplateId + " not found"));
//
//            System.out.println(inventoryItemTemplate.getItemType());
//        }
//
//        InventoryItem inventoryItem = InventoryItem.builder()
//                .quantity(1)
//                .itemTemplate(inventoryItemTemplate)
//                .build();
//
//        InventoryItem savedInventoryItem = inventoryItemRepository.save(inventoryItem);
////        //add it to inventory slot
//        inventorySlotRepository.addInventoryItemToEmptySlotByPositionAndInventoryId(savedInventoryItem.getId(), position, inventoryId);
//    }

    @Transactional
    public void moveItem(int previousPosition, int newPosition, Long inventoryId) {
        // Get both slots
        List<InventorySlot> slots = inventorySlotRepository.findByInventoryIdAndPositionIn(inventoryId, List.of(previousPosition, newPosition));

        InventorySlot prevSlot = slots.stream()
                .filter(slot -> slot.getPosition() == previousPosition)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Previous slot not found"));

        InventorySlot newSlot = slots.stream()
                .filter(slot -> slot.getPosition() == newPosition)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("New slot not found"));


        ItemInstance prevItemInstance = prevSlot.getItemInstance();
        ItemInstance newItemInstance = newSlot.getItemInstance();

        // Step 1: Set previous slot item to null (to avoid unique constraint violation)
        inventorySlotRepository.updateInventoryItemBySlotId(null, prevSlot.getId());

        // Step 2: Move item from new slot to previous slot
        inventorySlotRepository.updateInventoryItemBySlotId(prevItemInstance != null ? prevItemInstance.getId() : null, newSlot.getId());

        // Step 3: Move item from previous slot to new slot
        inventorySlotRepository.updateInventoryItemBySlotId(newItemInstance != null ? newItemInstance.getId() : null, prevSlot.getId());
    }




}
