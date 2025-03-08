package dev.zymixon.inventory_service.dto.template;

import dev.zymixon.inventory_service.enums.WeaponType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class WeaponItemTemplateDto extends ItemTemplateDto {

    protected WeaponType weaponType;

}
