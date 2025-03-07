package dev.zymixon.inventory_service.mappers.instance;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.dto.instance.CommonItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.impl.CommonItemInstance;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.ItemMapper;
import dev.zymixon.inventory_service.mappers.template.CommonItemTemplateMapper;
import org.springframework.stereotype.Service;

@Service
public class CommonItemInstanceMapper implements ItemMapper {

    private final CommonItemTemplateMapper commonItemTemplateMapper;

    public CommonItemInstanceMapper(CommonItemTemplateMapper commonItemTemplateMapper) {
        this.commonItemTemplateMapper = commonItemTemplateMapper;
    }

    private CommonItemInstanceDto mapToDto(CommonItemInstance commonItemInstance) {

        return CommonItemInstanceDto.builder()
                .id(commonItemInstance.getId())
                .commonItemTemplate(commonItemTemplateMapper.mapToDto(commonItemInstance.getCommonItemTemplate()))
//                .type("common")
                .type(String.valueOf(ItemType.COMMON_ITEM_INSTANCE))
                .build();



    }

    @Override
    public ItemInstanceDto mapToDto(ItemInstance itemInstance) {
        // Map to CommonItemInstanceDto
        if (itemInstance instanceof CommonItemInstance) {
            CommonItemInstance commonItem = (CommonItemInstance) itemInstance;
            // Populate the DTO fields here
            return mapToDto(commonItem);
        }
        throw new IllegalArgumentException("Item is not of type CommonItemInstance");
    }
}
