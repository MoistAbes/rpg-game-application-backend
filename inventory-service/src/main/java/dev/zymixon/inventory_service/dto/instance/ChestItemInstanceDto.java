package dev.zymixon.inventory_service.dto.instance;

import dev.zymixon.inventory_service.dto.template.ChestItemTemplateDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ChestItemInstanceDto extends ArmorItemInstanceDto{

    private ChestItemTemplateDto chestTemplate;

    @Override
    public String toString() {
        return "ChestItemInstanceDto{" +
                "chestTemplate=" + chestTemplate +
                "armorValue=" + super.getArmorValue() +
                ", type='" + type + '\'' +
                '}';
    }
}
