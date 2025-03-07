package dev.zymixon.inventory_service.dto.instance;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class EquipmentItemInstanceDto extends ItemInstanceDto {

    private int quality;
    private int levelRequirement;

    @Override
    public String toString() {
        return "EquipmentItemInstanceDto{" +
                "quality=" + quality +
                ", levelRequirement=" + levelRequirement +
                " " + super.toString() +
                '}';
    }
}
