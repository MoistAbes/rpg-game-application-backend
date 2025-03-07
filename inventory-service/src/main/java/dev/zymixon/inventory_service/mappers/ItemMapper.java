package dev.zymixon.inventory_service.mappers;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;

public interface ItemMapper {
    ItemInstanceDto mapToDto(ItemInstance itemInstance);
}
