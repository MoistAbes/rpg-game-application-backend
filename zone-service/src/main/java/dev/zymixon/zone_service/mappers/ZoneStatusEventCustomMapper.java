package dev.zymixon.zone_service.mappers;

import dev.zymixon.zone_service.dto.ZoneStatusEventDto;
import dev.zymixon.zone_service.entities.ZoneStatusEvent;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ZoneStatusEventCustomMapper {

    public ZoneStatusEventDto toDto(ZoneStatusEvent zoneStatusEvent) {
        return ZoneStatusEventDto.builder()
                .id(zoneStatusEvent.getId())
                .name(zoneStatusEvent.getName())
                .description(zoneStatusEvent.getDescription())
                .build();

    }

    public Set<ZoneStatusEventDto> toDtoSet(Set<ZoneStatusEvent> zoneStatusEventList) {
        return zoneStatusEventList.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

}
