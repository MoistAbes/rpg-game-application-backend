package dev.zymixon.zone_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "integer default 1")
    private int minEnemyLevel;
    @Column(columnDefinition = "integer default 1")
    private int maxEnemyLevel;

    @Column(columnDefinition = "integer default 1")
    private int position;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    @ElementCollection
    @CollectionTable(name = "location_enemy_tiers", joinColumns = @JoinColumn(name = "location_id"))
    @Column(name = "enemy_tier")
    private Set<Integer> allowedTiers;  // Tiers like 1, 2, 3

    @ElementCollection
    @CollectionTable(
            name = "location_special_enemies",
            joinColumns = @JoinColumn(name = "location_id")
    )
    @Column(name = "enemy_id")
    private Set<Long> specialEnemyIds;  // Store enemy IDs instead of EnemyTemplate entities


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", allowedTiers=" + allowedTiers +
                ", specialEnemyIds=" + specialEnemyIds +
                ", minEnemyLevel=" + minEnemyLevel +
                ", maxEnemyLevel=" + maxEnemyLevel +
//                ", zone id=" + zone.getId() +
                '}';
    }
}
