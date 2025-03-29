package dev.zymixon.combat_service.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CombatResultUpdate {

    private Long characterId;
    private int experience;
    private int level;
    private Long characterStatsId;
    private int currentHealth;

}
