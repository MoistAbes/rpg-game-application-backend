package dev.zymixon.inventory_service.dto;

import dev.zymixon.inventory_service.enums.ItemType;
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



}
