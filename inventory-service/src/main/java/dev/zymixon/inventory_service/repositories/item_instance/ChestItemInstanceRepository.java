package dev.zymixon.inventory_service.repositories.item_instance;

import dev.zymixon.inventory_service.entities.instance.impl.ChestItemInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChestItemInstanceRepository extends JpaRepository<ChestItemInstance, Long> {
}
