package dev.zymixon.inventory_service.mappers.template;

import dev.zymixon.inventory_service.dto.template.SwordItemTemplateDto;
import dev.zymixon.inventory_service.entities.template.SwordItemTemplate;
import org.springframework.stereotype.Service;

@Service
public class SwordItemTemplateMapper {

    public SwordItemTemplateDto mapToDto(SwordItemTemplate swordItemTemplate) {

        return SwordItemTemplateDto.builder()
                .id(swordItemTemplate.getId())
                .name(swordItemTemplate.getName())
                .description(swordItemTemplate.getDescription())
                .iconPath(swordItemTemplate.getIconPath())
                .weaponType(swordItemTemplate.getWeaponType())
                .isEnemyDrop(swordItemTemplate.isEnemyDrop())
                .isStackable(swordItemTemplate.isStackable())
                .build();
    }

}
