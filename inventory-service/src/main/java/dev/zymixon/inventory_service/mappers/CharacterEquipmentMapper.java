package dev.zymixon.inventory_service.mappers;

import dev.zymixon.inventory_service.dto.CharacterEquipmentDto;
import dev.zymixon.inventory_service.entities.equipment.CharacterEquipment;
import dev.zymixon.inventory_service.mappers.instance.ItemInstanceMapper;
import org.springframework.stereotype.Service;

@Service
public class CharacterEquipmentMapper {

    private final ItemInstanceMapper itemInstanceMapper;

    public CharacterEquipmentMapper(ItemInstanceMapper itemInstanceMapper) {
        this.itemInstanceMapper = itemInstanceMapper;
    }

    public CharacterEquipmentDto mapToDto(CharacterEquipment characterEquipment) {
        return CharacterEquipmentDto.builder()
                .id(characterEquipment.getId())
                .helmet(itemInstanceMapper.mapToDto(characterEquipment.getHelmet()))
                .chest(itemInstanceMapper.mapToDto(characterEquipment.getChest()))
                .gloves(itemInstanceMapper.mapToDto(characterEquipment.getGloves()))
                .boots(itemInstanceMapper.mapToDto(characterEquipment.getBoots()))
                .mainHand(itemInstanceMapper.mapToDto(characterEquipment.getMainHand()))
                .build();
    }
}
