package dev.zymixon.inventory_service.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDropGenerateRequest {

    private Integer enemyLevel;
    private List<Long> freeInventorySlotsIds;



}
