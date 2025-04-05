package dev.zymixon.inventory_service.services;

import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.repositories.item_instance.ItemInstanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInstanceService {

    private final ItemInstanceRepository itemInstanceRepository;

    public ItemInstanceService(ItemInstanceRepository itemInstanceRepository) {
        this.itemInstanceRepository = itemInstanceRepository;
    }


    public List<ItemInstance> getAllItemInstanceByIds(List<Long> itemInstanceIdList) {


        List<ItemInstance> itemInstances = itemInstanceRepository.findAllById(itemInstanceIdList);

        System.out.println("ITEM INSTANCES");
        for (ItemInstance itemInstance : itemInstances) {
            System.out.println(itemInstance);
        }

        return itemInstances;

    }

    public void deleteItemsByIds(List<Long> itemInstanceIdList) {
        itemInstanceRepository.deleteAllById(itemInstanceIdList);
    }
}
