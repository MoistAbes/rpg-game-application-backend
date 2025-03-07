package dev.zymixon.inventory_service.mappers.template;

import dev.zymixon.inventory_service.dto.template.HelmetItemTemplateDto;
import dev.zymixon.inventory_service.entities.template.HelmetItemTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelmetItemTemplateMapper {

    public HelmetItemTemplateDto mapToDto(HelmetItemTemplate helmetItemTemplate) {
        return HelmetItemTemplateDto.builder()
                .id(helmetItemTemplate.getId())
                .name(helmetItemTemplate.getName())
                .description(helmetItemTemplate.getDescription())
                .iconPath(helmetItemTemplate.getIconPath())
                .isEnemyDrop(helmetItemTemplate.isEnemyDrop())
                .isStackable(helmetItemTemplate.isStackable())
                .build();
    }

}
