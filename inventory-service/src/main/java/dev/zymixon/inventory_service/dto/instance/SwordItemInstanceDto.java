package dev.zymixon.inventory_service.dto.instance;

import dev.zymixon.inventory_service.dto.template.SwordItemTemplateDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SwordItemInstanceDto extends WeaponItemInstanceDto {

    private SwordItemTemplateDto swordTemplate;

    @Override
    public String toString() {
        return "SwordItemInstanceDto{" +
                "swordTemplate=" + swordTemplate +
                "damageValue=" + super.getDamageValue() +
                ", type='" + type + '\'' +
                '}';
    }
}
