package dev.zymixon.inventory_service.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CharacterInventoryDto {
    private Long id;
    private List<InventorySlotDto> inventorySlots;

    @Override
    public String toString() {
        return "CharacterInventoryDto{" +
                "id=" + id +
                ", inventorySlots=" + inventorySlots +
                '}';
    }
}
