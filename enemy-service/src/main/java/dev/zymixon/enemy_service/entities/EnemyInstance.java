package dev.zymixon.enemy_service.entities;

import dev.zymixon.enemy_service.enums.EnemyRank;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnemyInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long currentHealth;
    private Long maxHealth;
    private int currentArmor;
    private int currentAttack;

    private int level;

    private EnemyRank rank;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private EnemyTemplate template;




    @Override
    public String toString() {
        return "EnemyInstance{" +
                "id=" + id +
                ", currentHealth=" + currentHealth +
                ", maxHealth=" + maxHealth +
                ", currentArmor=" + currentArmor +
                ", currentAttack=" + currentAttack +
                ", level=" + level +
                ", rank=" + rank +
                ", template=" + template +
                '}';
    }
}
