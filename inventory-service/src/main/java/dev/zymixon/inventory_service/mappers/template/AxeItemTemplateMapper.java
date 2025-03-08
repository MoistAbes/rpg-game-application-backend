package dev.zymixon.inventory_service.mappers.template;

import dev.zymixon.inventory_service.dto.template.AxeItemTemplateDto;
import dev.zymixon.inventory_service.entities.template.AxeItemTemplate;
import org.springframework.stereotype.Service;

@Service
public class AxeItemTemplateMapper {

    public AxeItemTemplateDto mapToDto(AxeItemTemplate axeItemTemplate) {

        return AxeItemTemplateDto.builder()
                .id(axeItemTemplate.getId())
                .name(axeItemTemplate.getName())
                .description(axeItemTemplate.getDescription())
                .iconPath(axeItemTemplate.getIconPath())
                .weaponType(axeItemTemplate.getWeaponType())
                .isEnemyDrop(axeItemTemplate.isEnemyDrop())
                .isStackable(axeItemTemplate.isStackable())
                .build();
    }

}
