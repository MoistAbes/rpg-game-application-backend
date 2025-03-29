package dev.zymixon.combat_service.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CharacterStats {

    private Long id;

    private int attack;
    private int defense;

    private int maxHealth;
    private int currentHealth;
}
