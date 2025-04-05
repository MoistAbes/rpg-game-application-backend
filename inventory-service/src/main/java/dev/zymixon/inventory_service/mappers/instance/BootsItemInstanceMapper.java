package dev.zymixon.inventory_service.mappers.instance;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.dto.instance.BootsItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.instance.impl.BootsItemInstance;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.ItemMapper;
import dev.zymixon.inventory_service.mappers.ItemStatMapper;
import dev.zymixon.inventory_service.mappers.template.BootsItemTemplateMapper;
import org.springframework.stereotype.Service;

@Service
public class BootsItemInstanceMapper implements ItemMapper {

    private final BootsItemTemplateMapper bootsItemTemplateMapper;
    private final ItemStatMapper itemStatMapper;


    public BootsItemInstanceMapper(BootsItemTemplateMapper bootsItemTemplateMapper, ItemStatMapper itemStatMapper) {
        this.bootsItemTemplateMapper = bootsItemTemplateMapper;
        this.itemStatMapper = itemStatMapper;
    }


    private BootsItemInstanceDto mapToDto(BootsItemInstance bootsItemInstance) {
        return BootsItemInstanceDto.builder()
                .id(bootsItemInstance.getId())
                .bootsTemplate(bootsItemTemplateMapper.mapToDto(bootsItemInstance.getBootsTemplate()))
                .armorValue(bootsItemInstance.getArmorValue())
                .type(String.valueOf(ItemType.BOOTS_ITEM_INSTANCE))
                .armorType(bootsItemInstance.getArmorType())
                .levelRequirement(bootsItemInstance.getLevelRequirement())
                .itemRarity(String.valueOf(bootsItemInstance.getItemRarity()))
                .itemStats(itemStatMapper.mapListToDto(bootsItemInstance.getStats()))
                .build();
    }


    @Override
    public ItemInstanceDto mapToDto(ItemInstance itemInstance) {
        if (itemInstance instanceof BootsItemInstance) {
            BootsItemInstance commonItem = (BootsItemInstance) itemInstance;
            // Populate the DTO fields here
            return mapToDto(commonItem);
        }
        throw new IllegalArgumentException("Item is not of type bootsItemInstance");
    }
}
