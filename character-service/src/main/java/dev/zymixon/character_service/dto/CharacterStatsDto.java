package dev.zymixon.character_service.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterStatsDto {
    private int attack;
    private int defense;
    private int maxHealth;
    private int currentHealth;


}
