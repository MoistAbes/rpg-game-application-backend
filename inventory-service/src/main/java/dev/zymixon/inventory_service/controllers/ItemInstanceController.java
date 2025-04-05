package dev.zymixon.inventory_service.controllers;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.mappers.instance.ItemInstanceMapper;
import dev.zymixon.inventory_service.services.ItemInstanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory-service/item-instance")
public class ItemInstanceController {

    private final ItemInstanceService itemInstanceService;
    private final ItemInstanceMapper itemInstanceMapper;

    public ItemInstanceController(ItemInstanceService itemInstanceService, ItemInstanceMapper itemInstanceMapper) {
        this.itemInstanceService = itemInstanceService;
        this.itemInstanceMapper = itemInstanceMapper;
    }


    @PostMapping("/get-all-instance-by-ids")
    public List<ItemInstanceDto> getAllItemInstanceByIds(@RequestBody List<Long> itemInstanceIdList) {
        List<ItemInstance> itemInstances = itemInstanceService.getAllItemInstanceByIds(itemInstanceIdList);

        List<ItemInstanceDto> mappedItemInstances = itemInstanceMapper.mapToDtoList(itemInstances);

        System.out.println("MAPPED ITEM INSTANCES");
        for (ItemInstanceDto itemInstanceDto : mappedItemInstances) {
            System.out.println(itemInstanceDto);
        }

        return mappedItemInstances;

    }


}
