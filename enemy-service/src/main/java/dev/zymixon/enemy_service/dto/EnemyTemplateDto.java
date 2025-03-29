package dev.zymixon.enemy_service.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EnemyTemplateDto {
    private Long id;
    private String name;
    private Long health;
    private int armor;
    private int attack;
    private String iconPath;
    private Integer enemyTier;
    private Set<EnemyTypeDto> enemyTypes;
}
