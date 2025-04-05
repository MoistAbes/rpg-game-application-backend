package dev.zymixon.character_service.mappers;

import dev.zymixon.character_service.dto.CharacterStatsDto;
import dev.zymixon.character_service.entities.CharacterStats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Enable Spring dependency injection
public interface  CharacterStatsMapper {

    @Mapping(target = "id", source = "id") // Explicitly map id
    @Mapping(target = "criticalChance", source = "criticalChance")
    @Mapping(target = "criticalDamage", source = "criticalDamage")
    CharacterStatsDto toDto(CharacterStats characterStats);

}

