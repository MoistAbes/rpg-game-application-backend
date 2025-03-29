package dev.zymixon.combat_service.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CombatRequestDto {

    private MyCharacter playerCharacter;
    private EnemyInstance enemyInstance;

}
