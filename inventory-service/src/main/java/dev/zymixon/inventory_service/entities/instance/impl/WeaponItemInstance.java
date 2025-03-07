package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.instance.EquipmentItemInstance;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("WEAPON")
@Getter
@Setter
public class WeaponItemInstance extends EquipmentItemInstance {

    private int damageValue;

    @Override
    public String toString() {
        return "WeaponItemInstance{" +
                "damageValue=" + damageValue +
                super.toString() +
                '}';
    }
}
