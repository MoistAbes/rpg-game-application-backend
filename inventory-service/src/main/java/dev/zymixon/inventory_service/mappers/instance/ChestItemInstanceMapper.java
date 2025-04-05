package dev.zymixon.inventory_service.mappers.instance;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.dto.instance.ChestItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.impl.ChestItemInstance;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.ItemMapper;
import dev.zymixon.inventory_service.mappers.ItemStatMapper;
import dev.zymixon.inventory_service.mappers.template.ChestItemTemplateMapper;
import org.springframework.stereotype.Service;

@Service
public class ChestItemInstanceMapper implements ItemMapper {

    private final ChestItemTemplateMapper chestItemTemplateMapper;
    private final ItemStatMapper itemStatMapper;


    public ChestItemInstanceMapper(ChestItemTemplateMapper chestItemTemplateMapper, ItemStatMapper itemStatMapper) {
        this.chestItemTemplateMapper = chestItemTemplateMapper;
        this.itemStatMapper = itemStatMapper;
    }

    private ChestItemInstanceDto mapToDto(ChestItemInstance chestItemInstance) {
        return ChestItemInstanceDto.builder()
                .id(chestItemInstance.getId())
                .chestTemplate(chestItemTemplateMapper.mapToDto(chestItemInstance.getChestTemplate()))
                .armorValue(chestItemInstance.getArmorValue())
                .type(String.valueOf(ItemType.CHEST_ITEM_INSTANCE))
                .levelRequirement(chestItemInstance.getLevelRequirement())
                .armorType(chestItemInstance.getArmorType())
                .itemRarity(String.valueOf(chestItemInstance.getItemRarity()))
                .itemStats(itemStatMapper.mapListToDto(chestItemInstance.getStats()))

                .build();
    }

    @Override
    public ItemInstanceDto mapToDto(ItemInstance itemInstance) {
        if (itemInstance instanceof ChestItemInstance) {
            ChestItemInstance commonItem = (ChestItemInstance) itemInstance;
            // Populate the DTO fields here
            return mapToDto(commonItem);
        }
        throw new IllegalArgumentException("Item is not of type chestItemInstance");
    }
}
