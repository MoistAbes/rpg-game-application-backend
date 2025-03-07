package dev.zymixon.inventory_service.repositories;

import dev.zymixon.inventory_service.entities.inventory.CharacterInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterInventoryRepository extends JpaRepository<CharacterInventory, Long> {

    @Query("SELECT i FROM CharacterInventory i JOIN FETCH i.inventorySlots s WHERE i.characterId = :characterId ORDER BY s.position ASC")
    Optional<CharacterInventory> findCharacterInventoryByCharacterId(@Param("characterId") Long characterId);


}
