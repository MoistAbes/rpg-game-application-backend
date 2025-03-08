package dev.zymixon.inventory_service.dto.instance;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class WeaponItemInstanceDto extends EquipmentItemInstanceDto {

    private int damageValue;


    @Override
    public String toString() {
        return "WeaponItemInstanceDto{" +
                " " + super.toString() +
                "damageValue=" + damageValue +
                '}';
    }
}
