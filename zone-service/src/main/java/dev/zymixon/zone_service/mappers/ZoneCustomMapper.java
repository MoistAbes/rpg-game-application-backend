package dev.zymixon.zone_service.mappers;

import dev.zymixon.zone_service.dto.ZoneDto;
import dev.zymixon.zone_service.entities.Zone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoneCustomMapper {

    private final LocationCustomMapper locationCustomMapper;
    private final ZoneStatusEventCustomMapper zoneStatusEventCustomMapper;

    public ZoneCustomMapper(LocationCustomMapper locationCustomMapper, ZoneStatusEventCustomMapper zoneStatusEventCustomMapper) {
        this.locationCustomMapper = locationCustomMapper;
        this.zoneStatusEventCustomMapper = zoneStatusEventCustomMapper;
    }

    public ZoneDto toDto(Zone zone) {
        return ZoneDto.builder()
                .id(zone.getId())
                .name(zone.getName())
                .description(zone.getDescription())
                .positionX(zone.getPositionX())
                .positionY(zone.getPositionY())
                .iconPath(zone.getIconPath())
                .locations(locationCustomMapper.toDtoList(zone.getLocations()))
                .allowedEnemyTypes(zone.getAllowedEnemyTypes())
                .statuses(zoneStatusEventCustomMapper.toDtoSet(zone.getStatuses()))
                .build();
    }

    public List<ZoneDto> toDtoList(List<Zone> zones) {
        return zones.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ZoneDto toDtoWithoutLocations(Zone zone) {
        return ZoneDto.builder()
                .id(zone.getId())
                .name(zone.getName())
                .description(zone.getDescription())
                .positionX(zone.getPositionX())
                .positionY(zone.getPositionY())
                .iconPath(zone.getIconPath())
                .allowedEnemyTypes(zone.getAllowedEnemyTypes())
                .statuses(zoneStatusEventCustomMapper.toDtoSet(zone.getStatuses()))
                .build();
    }

    public List<ZoneDto> toDtoWithoutLocationsList(List<Zone> zones) {
        return zones.stream()
                .map(this::toDtoWithoutLocations)
                .collect(Collectors.toList());
    }




}
