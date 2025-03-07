package dev.zymixon.character_service.mappers;

import dev.zymixon.character_service.dto.CharacterStatsDto;
import dev.zymixon.character_service.entities.CharacterStats;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Enable Spring dependency injection
public interface  CharacterStatsMapper {

    CharacterStatsDto toDto(CharacterStats characterStats);

}

