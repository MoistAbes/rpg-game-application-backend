package dev.zymixon.inventory_service.repositories.item_instance;

import dev.zymixon.inventory_service.entities.instance.impl.AxeItemInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AxeItemInstanceRepository extends JpaRepository<AxeItemInstance, Long> {
}
