package dev.zymixon.inventory_service.entities.instance;

import dev.zymixon.inventory_service.enums.ItemRarity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class EquipmentItemInstance extends ItemInstance {

    private int quality;
    private int levelRequirement;

    @Override
    public String toString() {
        return "EquipmentItemInstance{" +
                "quality=" + quality +
                ", levelRequirement=" + levelRequirement +
                super.toString() +
                '}';
    }
}
