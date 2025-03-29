package dev.zymixon.zone_service.mappers;

import dev.zymixon.zone_service.dto.LocationDto;
import dev.zymixon.zone_service.entities.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);


    LocationDto locationToLocationDto(Location location);
    Location locationDtoToLocation(LocationDto locationDto);

    List<LocationDto> locationsToLocationDtos(List<Location> locations);

}
