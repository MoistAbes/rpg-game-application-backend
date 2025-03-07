package dev.zymixon.inventory_service.mappers;

import dev.zymixon.inventory_service.dto.InventorySlotDto;
import dev.zymixon.inventory_service.entities.inventory.InventorySlot;
import dev.zymixon.inventory_service.mappers.instance.ItemInstanceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventorySlotMapper {

    private final ItemInstanceMapper itemInstanceMapper;

    public InventorySlotMapper(ItemInstanceMapper itemInstanceMapper) {

        this.itemInstanceMapper = itemInstanceMapper;
    }

    public InventorySlotDto mapToDto(InventorySlot inventorySlot) {

        return InventorySlotDto.builder()
                .id(inventorySlot.getId())
                .position(inventorySlot.getPosition())
                .itemInstance(itemInstanceMapper.mapToDto(inventorySlot.getItemInstance()))
                .build();

    }


    public List<InventorySlotDto> mapToDtoList(List<InventorySlot> inventorySlots) {

        return inventorySlots.stream()
                .map(this::mapToDto)
                .toList();

    }

}
