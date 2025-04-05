package dev.zymixon.character_service.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CharacterStatsDto {
    private Long id;
    private int attack;
    private int defense;
    private int maxHealth;
    private int currentHealth;

    private float criticalChance;
    private float criticalDamage;


}
