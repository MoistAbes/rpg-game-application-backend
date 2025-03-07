package dev.zymixon.inventory_service.mappers.template;

import dev.zymixon.inventory_service.dto.template.ChestItemTemplateDto;
import dev.zymixon.inventory_service.entities.template.ChestItemTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChestItemTemplateMapper {

    public ChestItemTemplateDto mapToDto(ChestItemTemplate chestItemTemplate) {
        return ChestItemTemplateDto.builder()
                .id(chestItemTemplate.getId())
                .name(chestItemTemplate.getName())
                .description(chestItemTemplate.getDescription())
                .iconPath(chestItemTemplate.getIconPath())
                .isEnemyDrop(chestItemTemplate.isEnemyDrop())
                .isStackable(chestItemTemplate.isStackable())
                .build();
    }

}
