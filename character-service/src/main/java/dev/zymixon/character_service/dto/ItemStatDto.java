package dev.zymixon.character_service.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemStatDto {

    private Double value;
    private String itemStatType;

    @Override
    public String toString() {
        return "ItemStatDto{" +
                "value=" + value +
                ", itemStatType='" + itemStatType + '\'' +
                '}';
    }
}
