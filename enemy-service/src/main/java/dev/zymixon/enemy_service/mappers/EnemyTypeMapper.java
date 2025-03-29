package dev.zymixon.enemy_service.mappers;

import dev.zymixon.enemy_service.dto.EnemyTypeDto;
import dev.zymixon.enemy_service.entities.EnemyType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnemyTypeMapper {

    EnemyTypeDto toDto(EnemyType enemyType);

}
