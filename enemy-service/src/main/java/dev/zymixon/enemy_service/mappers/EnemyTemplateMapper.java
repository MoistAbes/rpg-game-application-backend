package dev.zymixon.enemy_service.mappers;

import dev.zymixon.enemy_service.dto.EnemyTemplateDto;
import dev.zymixon.enemy_service.entities.EnemyTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnemyTemplateMapper {

    @Mapping(source = "enemyTypes", target = "enemyTypes") // Map full object
    @Mapping(source = "enemyTier", target = "enemyTier")
    EnemyTemplateDto toDto(EnemyTemplate entity);

    @Mapping(source = "enemyTypes", target = "enemyTypes") // Map full object
    @Mapping(source = "enemyTier", target = "enemyTier")
    EnemyTemplate toEntity(EnemyTemplateDto dto);
}
