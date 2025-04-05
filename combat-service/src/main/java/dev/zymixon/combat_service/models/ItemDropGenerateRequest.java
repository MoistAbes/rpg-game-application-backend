package dev.zymixon.combat_service.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ItemDropGenerateRequest {

    private Integer enemyLevel;
    private List<Long> freeInventorySlotsIds;


}
