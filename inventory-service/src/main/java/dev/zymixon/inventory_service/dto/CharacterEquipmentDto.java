package dev.zymixon.inventory_service.dto;


import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterEquipmentDto {

    private Long id;
    private ItemInstanceDto helmet;
    private ItemInstanceDto chest;
    private ItemInstanceDto gloves;
    private ItemInstanceDto boots;

    @Override
    public String toString() {
        return "CharacterEquipmentDto{" +
                "id=" + id +
                ", helmet=" + helmet +
                ", chest=" + chest +
                ", armor=" + gloves +
                '}';
    }
}
