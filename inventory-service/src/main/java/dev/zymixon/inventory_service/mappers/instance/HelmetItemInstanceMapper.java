package dev.zymixon.inventory_service.mappers.instance;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.dto.instance.HelmetItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.impl.HelmetItemInstance;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.ItemMapper;
import dev.zymixon.inventory_service.mappers.ItemStatMapper;
import dev.zymixon.inventory_service.mappers.template.HelmetItemTemplateMapper;
import org.springframework.stereotype.Service;

@Service
public class HelmetItemInstanceMapper implements ItemMapper {

    private final HelmetItemTemplateMapper helmetItemTemplateMapper;
    private final ItemStatMapper itemStatMapper;

    public HelmetItemInstanceMapper(HelmetItemTemplateMapper helmetItemTemplateMapper, ItemStatMapper itemStatMapper) {
        this.helmetItemTemplateMapper = helmetItemTemplateMapper;
        this.itemStatMapper = itemStatMapper;
    }

    private HelmetItemInstanceDto mapToDto(HelmetItemInstance helmetItemInstance) {
        return HelmetItemInstanceDto.builder()
                .id(helmetItemInstance.getId())
                .helmetTemplate(helmetItemTemplateMapper.mapToDto(helmetItemInstance.getHelmetTemplate()))
                .armorValue(helmetItemInstance.getArmorValue())
                .type(String.valueOf(ItemType.HELMET_ITEM_INSTANCE))
                .levelRequirement(helmetItemInstance.getLevelRequirement())
                .armorType(helmetItemInstance.getArmorType())
                .itemRarity(String.valueOf(helmetItemInstance.getItemRarity()))
                .itemStats(itemStatMapper.mapListToDto(helmetItemInstance.getStats()))
                .build();
    }

    @Override
    public ItemInstanceDto mapToDto(ItemInstance itemInstance) {
        if (itemInstance instanceof HelmetItemInstance) {
            HelmetItemInstance commonItem = (HelmetItemInstance) itemInstance;
            // Populate the DTO fields here
            return mapToDto(commonItem);
        }
        throw new IllegalArgumentException("Item is not of type HelmetItemInstance");
    }
}
