package dev.zymixon.enemy_service.mappers;

import dev.zymixon.enemy_service.dto.EnemyInstanceDto;
import dev.zymixon.enemy_service.entities.EnemyInstance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnemyInstanceMapper {

    EnemyInstanceMapper INSTANCE = Mappers.getMapper(EnemyInstanceMapper.class);

    @Mapping(source = "template", target = "template") // Map full object
    @Mapping(source = "rank", target = "rank")
    EnemyInstanceDto toDto(EnemyInstance entity);

    @Mapping(source = "template", target = "template")
    @Mapping(source = "rank", target = "rank")
    EnemyInstance toEntity(EnemyInstanceDto dto);
}
