package dev.zymixon.inventory_service.dto.instance;

import dev.zymixon.inventory_service.enums.ArmorType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class ArmorItemInstanceDto extends EquipmentItemInstanceDto {
    private int armorValue;
    private ArmorType armorType;

    @Override
    public String toString() {
        return "ArmorItemInstanceDto{" +
                " " + super.toString() +
                "armorValue=" + armorValue +
                "ArmorType=" + armorType +
                '}';
    }
}
