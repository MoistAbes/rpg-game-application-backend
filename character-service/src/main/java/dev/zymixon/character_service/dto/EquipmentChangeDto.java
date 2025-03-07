package dev.zymixon.character_service.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentChangeDto {

    //what item are we equipping / undequipping
//    private ItemType itemType;

    private Long characterId;

    //prev item
    private List<ItemStatDto> prevItemStats;
    private int prevArmorValue;

    //new item
    private List<ItemStatDto> newItemStats;
    private int newArmorValue;

    @Override
    public String toString() {
        return "EquipmentChangeDto{" +
                "prevItemStats=" + prevItemStats +
                ", prevArmorValue=" + prevArmorValue +
                ", newItemStats=" + newItemStats +
                ", newArmorValue=" + newArmorValue +
                '}';
    }
}
