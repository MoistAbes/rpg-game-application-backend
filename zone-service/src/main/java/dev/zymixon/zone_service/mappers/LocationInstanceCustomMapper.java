package dev.zymixon.zone_service.mappers;

import dev.zymixon.zone_service.dto.LocationInstanceDto;
import dev.zymixon.zone_service.entities.LocationInstance;
import org.springframework.stereotype.Service;

@Service
public class LocationInstanceCustomMapper {

    private final LocationCustomMapper locationCustomMapper;

    public LocationInstanceCustomMapper(LocationCustomMapper locationCustomMapper) {
        this.locationCustomMapper = locationCustomMapper;
    }

    public LocationInstanceDto toDto(LocationInstance locationInstance) {

        if (locationInstance == null) {
            return null;
        }

        return LocationInstanceDto.builder()
                .id(locationInstance.getId())
                .location(locationCustomMapper.toDtoWithZone(locationInstance.getLocation()))
                .characterId(locationInstance.getCharacterId())
                .enemyInstanceId(locationInstance.getEnemyInstanceId())
                .build();

    }

}
