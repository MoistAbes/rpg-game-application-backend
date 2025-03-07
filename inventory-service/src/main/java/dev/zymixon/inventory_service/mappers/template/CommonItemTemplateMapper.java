package dev.zymixon.inventory_service.mappers.template;

import dev.zymixon.inventory_service.dto.template.CommonItemTemplateDto;
import dev.zymixon.inventory_service.entities.template.CommonItemTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommonItemTemplateMapper {

    public CommonItemTemplateDto mapToDto(CommonItemTemplate commonItemTemplate) {
        return CommonItemTemplateDto.builder()
                .id(commonItemTemplate.getId())
                .name(commonItemTemplate.getName())
                .description(commonItemTemplate.getDescription())
                .iconPath(commonItemTemplate.getIconPath())
                .isEnemyDrop(commonItemTemplate.isEnemyDrop())
                .isStackable(commonItemTemplate.isStackable())
                .build();
    }

}
