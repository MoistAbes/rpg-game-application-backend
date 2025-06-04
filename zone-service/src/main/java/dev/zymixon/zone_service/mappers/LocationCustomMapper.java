package dev.zymixon.zone_service.mappers;

import dev.zymixon.zone_service.dto.LocationDto;
import dev.zymixon.zone_service.entities.Location;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationCustomMapper {

    private final ZoneCustomMapper zoneCustomMapper;

    //Lazy tutaj pozwala nam uniknac zapentlenia wstrzykiwania zone i sprawia ze spring wstrzykuje go tylko wtegy gdy jest potrzebny
    public LocationCustomMapper(@Lazy ZoneCustomMapper zoneCustomMapper) {
        this.zoneCustomMapper = zoneCustomMapper;
    }

    public LocationDto toDto(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .name(location.getName())
                .allowedTiers(location.getAllowedTiers())
                .specialEnemyIds(location.getSpecialEnemyIds())
                .minEnemyLevel(location.getMinEnemyLevel())
                .maxEnemyLevel(location.getMaxEnemyLevel())
                .build();
    }

    public List<LocationDto> toDtoList(List<Location> locations) {
        return locations.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LocationDto toDtoWithZone(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .name(location.getName())
                .allowedTiers(location.getAllowedTiers())
                .specialEnemyIds(location.getSpecialEnemyIds())
                .minEnemyLevel(location.getMinEnemyLevel())
                .maxEnemyLevel(location.getMaxEnemyLevel())
                .zone(zoneCustomMapper.toDtoWithoutLocations(location.getZone()))
                .build();
    }

    public List<LocationDto> toDtoWithZoneList(List<Location> locations) {
        return locations.stream()
                .map(this::toDtoWithZone)
                .collect(Collectors.toList());
    }

}
