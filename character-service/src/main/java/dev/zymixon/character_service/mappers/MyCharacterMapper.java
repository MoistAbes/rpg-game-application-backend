package dev.zymixon.character_service.mappers;

import dev.zymixon.character_service.dto.MyCharacterDto;
import dev.zymixon.character_service.entities.MyCharacter;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CharacterStatsMapper.class) // Use the CharacterStatsMapper here
public interface  MyCharacterMapper {

    @Mapping(target = "nextLevelExperience", ignore = true) // Ignore extra field for now
    MyCharacterDto toDto(MyCharacter character);

    @InheritInverseConfiguration
    MyCharacter toEntity(MyCharacterDto characterDTO);

}
