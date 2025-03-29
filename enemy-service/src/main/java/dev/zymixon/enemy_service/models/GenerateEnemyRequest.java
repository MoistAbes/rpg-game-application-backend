package dev.zymixon.enemy_service.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GenerateEnemyRequest {

    private Long enemyTemplateId;
    private Integer minEnemyLevel;
    private Integer maxEnemyLevel;

}
