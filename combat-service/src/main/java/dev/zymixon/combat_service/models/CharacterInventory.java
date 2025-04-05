package dev.zymixon.combat_service.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CharacterInventory {

    private Long id;
    private List<InventorySlot> inventorySlots;

}
