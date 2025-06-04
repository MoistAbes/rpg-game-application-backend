package dev.zymixon.zone_service.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LocationInstanceDto {

    private Long id;
    private LocationDto location;
    private Long characterId;
    private List<Long> enemyInstanceId;

}
