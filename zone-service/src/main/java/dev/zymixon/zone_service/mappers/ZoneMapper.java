package dev.zymixon.zone_service.mappers;

import dev.zymixon.zone_service.dto.ZoneDto;
import dev.zymixon.zone_service.entities.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ZoneMapper {


    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);

    @Mapping(source = "locations", target = "locations")
    @Mapping(source = "allowedEnemyTypes", target = "allowedEnemyTypes")
    ZoneDto zoneToZoneDto(Zone zone);

    @Mapping(source = "locations", target = "locations")
    @Mapping(source = "allowedEnemyTypes", target = "allowedEnemyTypes")
    Zone zoneDtoToZone(ZoneDto zoneDto);

    List<ZoneDto> zonesToZoneDtos(List<Zone> zones);

}
