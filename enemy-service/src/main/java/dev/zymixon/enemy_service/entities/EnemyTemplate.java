package dev.zymixon.enemy_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnemyTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long health;
    private int armor;
    private int attack;

    private String iconPath;


    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnemyInstance> instances = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "enemy_template_enemy_type",
            joinColumns = @JoinColumn(name = "enemy_template_id"),
            inverseJoinColumns = @JoinColumn(name = "enemy_type_id")
    )
    private Set<EnemyType> enemyTypes; // Set of enemy types this enemy belongs to

    @Column(columnDefinition = "integer default 0")
    private Integer enemyTier;


    @Override
    public String toString() {
        return "EnemyTemplate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", armor=" + armor +
                ", attack=" + attack +
                ", iconPath='" + iconPath + '\'' +
                ", type='" + enemyTypes + '\'' +
                ", enemyTier='" + enemyTier + '\'' +
                '}';
    }
}
