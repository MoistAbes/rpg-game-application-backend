package dev.zymixon.enemy_service.dto;


import dev.zymixon.enemy_service.enums.EnemyRank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EnemyInstanceDto {

    private Long id;
    private Long currentHealth;
    private Long maxHealth;
    private int currentArmor;
    private int currentAttack;
    private int level;
    private EnemyRank rank;
    private EnemyTemplateDto template; // Store the full template object

}
