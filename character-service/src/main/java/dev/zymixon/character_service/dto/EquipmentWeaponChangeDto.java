package dev.zymixon.character_service.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EquipmentWeaponChangeDto {

    private Long characterId;

    //prev item
    private List<ItemStatDto> prevItemStats;
    private int prevDamageValue;

    //new item
    private List<ItemStatDto> newItemStats;
    private int newDamageValue;

}
