package dev.zymixon.inventory_service.entities.instance;

import dev.zymixon.inventory_service.enums.ItemRarity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
