package dev.zymixon.inventory_service.repositories;

import dev.zymixon.inventory_service.entities.inventory.InventorySlot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventorySlotRepository extends JpaRepository<InventorySlot, Long> {

//    @Transactional
//    @Modifying
//    @Query("UPDATE InventorySlot i SET i.inventoryItem.id = :inventoryItemId WHERE i.position = :position AND i.inventory.id = :inventoryId")
//    void addInventoryItemToEmptySlotByPositionAndInventoryId(@Param("inventoryItemId") Long inventoryItemId, @Param("position") int position, @Param("inventoryId") Long inventoryId);
//
    @Query("SELECT i FROM InventorySlot i WHERE i.inventory.id = :inventoryId AND i.position IN :swappedPositions")
    List<InventorySlot> findByInventoryIdAndPositionIn(Long inventoryId, List<Integer> swappedPositions);
//
    @Transactional
    @Modifying
    @Query("UPDATE InventorySlot s SET s.itemInstance.id = :itemId WHERE s.id = :slotId")
    void updateInventoryItemBySlotId(@Param("itemId") Long itemId, @Param("slotId") Long slotId);


    @Transactional
    @Modifying
    @Query("UPDATE InventorySlot s SET s.itemInstance = null WHERE s.id = :inventorySlotId")
    void clearInventorySlotItem(@Param("inventorySlotId") Long inventorySlotId);


}
