package dev.zymixon.inventory_service.dto.instance;

import dev.zymixon.inventory_service.dto.template.ChestItemTemplateDto;
import dev.zymixon.inventory_service.dto.template.GlovesItemTemplateDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class GlovesItemInstanceDto extends ArmorItemInstanceDto {

    private GlovesItemTemplateDto glovesTemplate;

    @Override
    public String toString() {
        return "GlovesItemInstanceDto{" +
                "glovesTemplate=" + glovesTemplate +
                "armorValue=" + super.getArmorValue() +
                ", type='" + type + '\'' +
                '}';
    }

}
