package dev.zymixon.zone_service.dto;

import dev.zymixon.zone_service.enums.EnemyTypeEnum;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ZoneDto {
    private Long id;
    private String name;
    private String description;
    private int positionX;
    private int positionY;
    private String iconPath;
    private List<LocationDto> locations;
    private Set<EnemyTypeEnum> allowedEnemyTypes;

}
