package dev.zymixon.inventory_service.dto;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventorySlotDto {

    private Long id;
    private int position;
    private ItemInstanceDto itemInstance;


    @Override
    public String toString() {
        return "InventorySlotDto{" +
                "id=" + id +
                ", position=" + position +
                ", itemInstance=" + itemInstance +
                '}';
    }
}
