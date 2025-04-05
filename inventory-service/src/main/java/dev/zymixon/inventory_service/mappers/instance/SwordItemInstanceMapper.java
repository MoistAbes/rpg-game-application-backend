package dev.zymixon.inventory_service.mappers.instance;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.dto.instance.SwordItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.instance.impl.SwordItemInstance;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.ItemMapper;
import dev.zymixon.inventory_service.mappers.ItemStatMapper;
import dev.zymixon.inventory_service.mappers.template.SwordItemTemplateMapper;
import org.springframework.stereotype.Service;

@Service
public class SwordItemInstanceMapper implements ItemMapper {

    private final SwordItemTemplateMapper swordItemTemplateMapper;
    private final ItemStatMapper itemStatMapper;

    public SwordItemInstanceMapper(SwordItemTemplateMapper swordItemTemplateMapper, ItemStatMapper itemStatMapper) {
        this.swordItemTemplateMapper = swordItemTemplateMapper;
        this.itemStatMapper = itemStatMapper;
    }

    private SwordItemInstanceDto mapToDto(SwordItemInstance swordItemInstance) {
        return SwordItemInstanceDto.builder()
                .id(swordItemInstance.getId())
                .swordTemplate(swordItemTemplateMapper.mapToDto(swordItemInstance.getSwordTemplate()))
                .damageValue(swordItemInstance.getDamageValue())
                .type(String.valueOf(ItemType.SWORD_ITEM_INSTANCE))
                .levelRequirement(swordItemInstance.getLevelRequirement())
                .itemRarity(String.valueOf(swordItemInstance.getItemRarity()))
                .itemStats(itemStatMapper.mapListToDto(swordItemInstance.getStats()))
                .build();
    }


    @Override
    public ItemInstanceDto mapToDto(ItemInstance itemInstance) {
        if (itemInstance instanceof SwordItemInstance) {
            SwordItemInstance commonItem = (SwordItemInstance) itemInstance;
            // Populate the DTO fields here
            return mapToDto(commonItem);
        }
        throw new IllegalArgumentException("Item is not of type SwordItemInstance");
    }
}
