package dev.zymixon.inventory_service.dto.instance;

import dev.zymixon.inventory_service.dto.template.BootsItemTemplateDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BootsItemInstanceDto extends ArmorItemInstanceDto {

    private BootsItemTemplateDto bootsTemplate;

    @Override
    public String toString() {
        return "BootsItemInstanceDto{" +
                "bootsTemplate=" + bootsTemplate +
                "armorValue=" + super.getArmorValue() +
                ", type='" + type + '\'' +
                '}';
    }
}
