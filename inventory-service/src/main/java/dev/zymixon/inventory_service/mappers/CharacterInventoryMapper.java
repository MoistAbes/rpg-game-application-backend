package dev.zymixon.inventory_service.mappers;

import dev.zymixon.inventory_service.dto.CharacterInventoryDto;
import dev.zymixon.inventory_service.entities.inventory.CharacterInventory;
import org.springframework.stereotype.Service;

@Service
public class CharacterInventoryMapper {

    private final InventorySlotMapper inventorySlotMapper;

    public CharacterInventoryMapper(InventorySlotMapper inventorySlotMapper) {
        this.inventorySlotMapper = inventorySlotMapper;
    }

    public CharacterInventoryDto mapToDto(CharacterInventory characterInventory) {

        return CharacterInventoryDto.builder()
                .id(characterInventory.getId())
                .inventorySlots(inventorySlotMapper.mapToDtoList(characterInventory.getInventorySlots()))
                .build();

    }

}
