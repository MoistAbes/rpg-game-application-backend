package dev.zymixon.zone_service.entities;

import dev.zymixon.zone_service.enums.EnemyTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(columnDefinition = "integer default 0")
    private int positionX;
    @Column(columnDefinition = "integer default 0")
    private int positionY;

    private String iconPath;


    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations = new ArrayList<>();

    @ElementCollection  // This tells Hibernate to store a collection of values
    @CollectionTable(   // Defines the table to store this collection
            name = "zone_enemy_types",  // Name of the extra table
            joinColumns = @JoinColumn(name = "zone_id") // Foreign key linking to Zone
    )
    @Column(name = "enemy_type") // Column name inside the extra table
    @Enumerated(EnumType.STRING) // Store the enum as a String
    private Set<EnemyTypeEnum> allowedEnemyTypes;

    @ManyToMany
    @JoinTable(
            name = "zone_status",  // Explicitly specify the join table name
            joinColumns = @JoinColumn(name = "zone_id"),  // Foreign key for Zone
            inverseJoinColumns = @JoinColumn(name = "zone_status_id")  // Foreign key for ZoneStatus
    )
    private Set<ZoneStatusEvent> statuses = new HashSet<>();  // A zone can have multiple statuses



    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", xPosition=" + positionX +
                ", yPosition=" + positionY +
                ", iconPath='" + iconPath + '\'' +
                ", allowedEnemyTypes='" + allowedEnemyTypes + '\'' +
                ", locations='" + locations + '\'' +
                '}';
    }
}
