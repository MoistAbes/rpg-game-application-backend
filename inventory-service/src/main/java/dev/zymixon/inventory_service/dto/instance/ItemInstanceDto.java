package dev.zymixon.inventory_service.dto.instance;

import dev.zymixon.inventory_service.dto.ItemStatDto;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ItemInstanceDto {

    private Long id;
    private int quantity;
    protected String type;
    private String itemRarity;
    private List<ItemStatDto> itemStats = new ArrayList<>();

    @Override
    public String toString() {
        return "ItemInstanceDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", itemRarity=" + itemRarity +
                ", itemStats=" + itemStats.size() +
                ", type='" + type + '\'' +
                '}';
    }
}
