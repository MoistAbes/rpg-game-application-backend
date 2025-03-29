package dev.zymixon.inventory_service.repositories.item_instance;

import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInstanceRepository extends JpaRepository<ItemInstance, Long> {
}
