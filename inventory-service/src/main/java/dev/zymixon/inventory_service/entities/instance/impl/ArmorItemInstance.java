package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.instance.EquipmentItemInstance;
import dev.zymixon.inventory_service.enums.ArmorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("ARMOR")
@Getter
@Setter
public class ArmorItemInstance extends EquipmentItemInstance {

    private int armorValue;
    private ArmorType armorType;

    @Override
    public String toString() {
        return "ArmorItemInstance{" +
                "armorValue=" + armorValue +
                "armor type=" + armorType +
                super.toString() +
                '}';
    }
}
