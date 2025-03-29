package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.instance.EquipmentItemInstance;
import dev.zymixon.inventory_service.enums.ArmorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("ARMOR")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
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
