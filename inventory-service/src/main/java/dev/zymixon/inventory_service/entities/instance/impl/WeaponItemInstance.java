package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.instance.EquipmentItemInstance;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("WEAPON")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
