package dev.zymixon.inventory_service.mappers.template;

import dev.zymixon.inventory_service.dto.template.GlovesItemTemplateDto;
import dev.zymixon.inventory_service.entities.template.GlovesItemTemplate;
import org.springframework.stereotype.Service;

@Service
public class GlovesItemTemplateMapper {

    public GlovesItemTemplateDto mapToDto(GlovesItemTemplate glovesItemTemplate) {
        return GlovesItemTemplateDto.builder()
                .id(glovesItemTemplate.getId())
                .name(glovesItemTemplate.getName())
                .description(glovesItemTemplate.getDescription())
                .iconPath(glovesItemTemplate.getIconPath())
                .isEnemyDrop(glovesItemTemplate.isEnemyDrop())
                .isStackable(glovesItemTemplate.isStackable())
                .build();
    }
}
