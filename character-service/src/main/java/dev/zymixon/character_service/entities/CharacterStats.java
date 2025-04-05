package dev.zymixon.character_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CharacterStats {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int attack;
    private int defense;

    private int maxHealth;
    private int currentHealth;

    @Column(columnDefinition = "FLOAT DEFAULT 5.0")
    private float criticalChance;

    @Column(columnDefinition = "FLOAT DEFAULT 150.0")
    private float criticalDamage;



}
