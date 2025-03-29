package dev.zymixon.combat_service.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CombatLog {

    private int turn;
    private String log;
    private int playerCurrentHealth;
    private Long enemyCurrentHealth;
}
