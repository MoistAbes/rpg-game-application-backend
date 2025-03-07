package dev.zymixon.inventory_service.repositories;

import dev.zymixon.inventory_service.entities.equipment.CharacterEquipment;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterEquipmentRepository extends JpaRepository<CharacterEquipment, Long> {

    @Query("SELECT i from CharacterEquipment i WHERE i.characterId = :characterId")
    Optional<CharacterEquipment> findByCharacterId(@Param("characterId") Long characterId);


    @Modifying
    @Transactional
    @Query("UPDATE CharacterEquipment ce SET ce.helmet = :itemInstance WHERE ce.id = :characterEquipmentId")
    void updateCharacterEquipmentHelmet(@Param("itemInstance") ItemInstance itemInstance, @Param("characterEquipmentId") Long characterEquipmentId);

    @Modifying
    @Transactional
    @Query("UPDATE CharacterEquipment ce SET ce.chest = :itemInstance WHERE ce.id = :characterEquipmentId")
    void updateCharacterEquipmentChest(@Param("itemInstance") ItemInstance itemInstance, @Param("characterEquipmentId") Long characterEquipmentId);

    @Modifying
    @Transactional
    @Query("UPDATE CharacterEquipment ce SET ce.gloves = :itemInstance WHERE ce.id = :characterEquipmentId")
    void updateCharacterEquipmentGloves(@Param("itemInstance") ItemInstance itemInstance, @Param("characterEquipmentId") Long characterEquipmentId);

    @Modifying
    @Transactional
    @Query("UPDATE CharacterEquipment ce SET ce.boots = :itemInstance WHERE ce.id = :characterEquipmentId")
    void updateCharacterEquipmentBoots(@Param("itemInstance") ItemInstance itemInstance, @Param("characterEquipmentId") Long characterEquipmentId);



}
