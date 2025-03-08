package dev.zymixon.inventory_service.dto.instance;

import dev.zymixon.inventory_service.dto.template.AxeItemTemplateDto;
import dev.zymixon.inventory_service.dto.template.SwordItemTemplateDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class AxeItemInstanceDto extends WeaponItemInstanceDto {

    private AxeItemTemplateDto axeTemplate;

    @Override
    public String toString() {
        return "AxeItemInstanceDto{" +
                "axeTemplate=" + axeTemplate +
                "damageValue=" + super.getDamageValue() +
                ", type='" + type + '\'' +
                '}';
    }

}
