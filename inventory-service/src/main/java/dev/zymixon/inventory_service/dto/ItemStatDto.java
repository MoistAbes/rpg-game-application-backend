package dev.zymixon.inventory_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemStatDto {

    private Long id;
    private Double value;
    private String itemStatType;

}
