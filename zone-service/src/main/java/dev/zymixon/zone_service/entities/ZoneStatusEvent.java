package dev.zymixon.zone_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZoneStatusEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "statuses")  // The inverse side of the Many-to-Many relationship
    private Set<Zone> zones = new HashSet<>();  // A status can be applied to multiple zones



}
