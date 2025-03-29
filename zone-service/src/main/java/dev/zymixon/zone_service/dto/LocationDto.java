package dev.zymixon.zone_service.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LocationDto {
    private Long id;
    private String name;
    private Set<Integer> allowedTiers;
    private Set<Long> specialEnemyIds;
    private Integer minEnemyLevel;
    private Integer maxEnemyLevel;
}
