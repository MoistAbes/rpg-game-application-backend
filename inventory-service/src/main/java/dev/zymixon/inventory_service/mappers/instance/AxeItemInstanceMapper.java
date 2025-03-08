package dev.zymixon.inventory_service.mappers.instance;

import dev.zymixon.inventory_service.dto.instance.AxeItemInstanceDto;
import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.instance.impl.AxeItemInstance;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.ItemMapper;
import dev.zymixon.inventory_service.mappers.ItemStatMapper;
import dev.zymixon.inventory_service.mappers.template.AxeItemTemplateMapper;
import org.springframework.stereotype.Service;

@Service
public class AxeItemInstanceMapper implements ItemMapper {

    private final AxeItemTemplateMapper axeItemTemplateMapper;
    private final ItemStatMapper itemStatMapper;


    public AxeItemInstanceMapper(AxeItemTemplateMapper axeItemTemplateMapper, ItemStatMapper itemStatMapper) {
        this.axeItemTemplateMapper = axeItemTemplateMapper;
        this.itemStatMapper = itemStatMapper;
    }


    private AxeItemInstanceDto mapToDto(AxeItemInstance axeItemInstance) {
        return AxeItemInstanceDto.builder()
                .id(axeItemInstance.getId())
                .axeTemplate(axeItemTemplateMapper.mapToDto(axeItemInstance.getAxeTemplate()))
                .damageValue(axeItemInstance.getDamageValue())
                .type(String.valueOf(ItemType.AXE_ITEM_INSTANCE))
                .itemRarity(String.valueOf(axeItemInstance.getItemRarity()))
                .itemStats(itemStatMapper.mapListToDto(axeItemInstance.getStats()))
                .build();
    }

    @Override
    public ItemInstanceDto mapToDto(ItemInstance itemInstance) {
        if (itemInstance instanceof AxeItemInstance) {
            AxeItemInstance commonItem = (AxeItemInstance) itemInstance;
            // Populate the DTO fields here
            return mapToDto(commonItem);
        }
        throw new IllegalArgumentException("Item is not of type AxeItemInstance");
    }
}
