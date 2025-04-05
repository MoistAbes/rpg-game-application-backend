package dev.zymixon.inventory_service.mappers.instance;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.dto.instance.GlovesItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.instance.impl.GlovesItemInstance;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.ItemMapper;
import dev.zymixon.inventory_service.mappers.ItemStatMapper;
import dev.zymixon.inventory_service.mappers.template.GlovesItemTemplateMapper;
import org.springframework.stereotype.Service;

@Service
public class GlovesItemInstanceMapper implements ItemMapper {

    private final GlovesItemTemplateMapper glovesItemTemplateMapper;
    private final ItemStatMapper itemStatMapper;

    public GlovesItemInstanceMapper(GlovesItemTemplateMapper glovesItemTemplateMapper, ItemStatMapper itemStatMapper) {
        this.glovesItemTemplateMapper = glovesItemTemplateMapper;
        this.itemStatMapper = itemStatMapper;
    }

    private GlovesItemInstanceDto mapToDto(GlovesItemInstance glovesItemInstance) {
        return GlovesItemInstanceDto.builder()
                .id(glovesItemInstance.getId())
                .glovesTemplate(glovesItemTemplateMapper.mapToDto(glovesItemInstance.getGlovesTemplate()))
                .armorValue(glovesItemInstance.getArmorValue())
                .type(String.valueOf(ItemType.GLOVES_ITEM_INSTANCE))
                .armorType(glovesItemInstance.getArmorType())
                .levelRequirement(glovesItemInstance.getLevelRequirement())
                .itemRarity(String.valueOf(glovesItemInstance.getItemRarity()))
                .itemStats(itemStatMapper.mapListToDto(glovesItemInstance.getStats()))
                .build();
    }


    @Override
    public ItemInstanceDto mapToDto(ItemInstance itemInstance) {
        if (itemInstance instanceof GlovesItemInstance) {
            GlovesItemInstance commonItem = (GlovesItemInstance) itemInstance;
            // Populate the DTO fields here
            return mapToDto(commonItem);
        }
        throw new IllegalArgumentException("Item is not of type chestItemInstance");
    }
}
