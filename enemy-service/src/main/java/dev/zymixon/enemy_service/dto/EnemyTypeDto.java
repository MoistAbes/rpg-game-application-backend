package dev.zymixon.enemy_service.dto;

import dev.zymixon.enemy_service.enums.EnemyTypeEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EnemyTypeDto {

    private EnemyTypeEnum type;
    private String description;

}
