package dev.zymixon.combat_service.models;

import dev.zymixon.combat_service.enums.EnemyRank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EnemyInstance {

    private Long id;
    private Long currentHealth;
    private Long maxHealth;
    private int currentArmor;
    private int currentAttack;
    private int level;
    private EnemyRank rank;
    private EnemyTemplate template;



}

