package dev.zymixon.inventory_service.services;

import dev.zymixon.inventory_service.models.ItemDropGenerateRequest;
import dev.zymixon.inventory_service.repositories.InventorySlotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventorySlotService {

    private final InventorySlotRepository inventorySlotRepository;
    private final ItemInstanceService itemInstanceService;

    public InventorySlotService(InventorySlotRepository inventorySlotRepository, ItemInstanceService itemInstanceService) {
        this.inventorySlotRepository = inventorySlotRepository;
        this.itemInstanceService = itemInstanceService;
    }

    public void updateInventorySlotItemInstance(Long itemInstanceId, Long slotId) {

        inventorySlotRepository.updateInventoryItemBySlotId(itemInstanceId, slotId);

    }


    /**
     * Handles the process of assigning dropped items to free inventory slots.
     * If there are more dropped items than free slots, the remaining items will be deleted.
     *
     * @param itemDropGenerateRequest Contains the list of free inventory slot IDs.
     * @param droppedItemIds List of item instance IDs that were dropped.
     */
    public void handleSavingDroppedItems(ItemDropGenerateRequest itemDropGenerateRequest, List<Long> droppedItemIds) {

        //check if we have any drops
        if (!droppedItemIds.isEmpty()) {
            // List to store items that couldn't be assigned to inventory slots
            List<Long> unplacedItemIds = new ArrayList<>();

            // Determine the number of available free slots and dropped items
            int freeSlots = itemDropGenerateRequest.getFreeInventorySlotsIds().size();
            int droppedItemCount = droppedItemIds.size();

            // Assign as many dropped items as possible to free inventory slots
            // Loop only as long as there are dropped items to assign
            for (int i = 0; i < Math.min(freeSlots, droppedItemIds.size()); i++) {
                updateInventorySlotItemInstance(
                        droppedItemIds.get(i),  // Item to assign
                        itemDropGenerateRequest.getFreeInventorySlotsIds().get(i) // Free slot ID
                );
            }


            // Collect remaining unplaced items if there are more items than free slots
            if (droppedItemCount > freeSlots) {
                unplacedItemIds = droppedItemIds.subList(freeSlots, droppedItemCount);
            }

            // Delete the unassigned items to prevent overflow in inventory
            if (!unplacedItemIds.isEmpty()) {
                itemInstanceService.deleteItemsByIds(unplacedItemIds);
            }
        }


    }

}
