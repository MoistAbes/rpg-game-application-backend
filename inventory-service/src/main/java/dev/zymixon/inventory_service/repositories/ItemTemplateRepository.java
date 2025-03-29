package dev.zymixon.inventory_service.repositories;

import dev.zymixon.inventory_service.entities.template.ItemTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemTemplateRepository extends JpaRepository<ItemTemplate, Long> {

    @Query("SELECT it FROM ItemTemplate it WHERE it.isEnemyDrop = true")
    List<ItemTemplate> findAllByIsEnemyDrop();

}
