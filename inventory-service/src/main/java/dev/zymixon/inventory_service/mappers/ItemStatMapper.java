package dev.zymixon.inventory_service.mappers;

import dev.zymixon.inventory_service.dto.ItemStatDto;
import dev.zymixon.inventory_service.entities.ItemStat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemStatMapper {

    public ItemStatDto mapToDto(ItemStat itemStat) {
        return ItemStatDto.builder()
                .id(itemStat.getId())
                .itemStatType(String.valueOf(itemStat.getStatType()))
                .value(itemStat.getValue())
                .build();
    }


    public List<ItemStatDto> mapListToDto(List<ItemStat> itemStatList) {

        return itemStatList.stream()
                .map(this::mapToDto)
                .toList();

    }

}
