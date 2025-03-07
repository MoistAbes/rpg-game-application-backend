package dev.zymixon.inventory_service.mappers.template;

import dev.zymixon.inventory_service.dto.template.BootsItemTemplateDto;
import dev.zymixon.inventory_service.dto.template.ChestItemTemplateDto;
import dev.zymixon.inventory_service.entities.template.BootsItemTemplate;
import dev.zymixon.inventory_service.entities.template.ChestItemTemplate;
import org.springframework.stereotype.Service;

@Service
public class BootsItemTemplateMapper {

    public BootsItemTemplateDto mapToDto(BootsItemTemplate bootsItemTemplate) {
        return BootsItemTemplateDto.builder()
                .id(bootsItemTemplate.getId())
                .name(bootsItemTemplate.getName())
                .description(bootsItemTemplate.getDescription())
                .iconPath(bootsItemTemplate.getIconPath())
                .isEnemyDrop(bootsItemTemplate.isEnemyDrop())
                .isStackable(bootsItemTemplate.isStackable())
                .build();
    }

}
