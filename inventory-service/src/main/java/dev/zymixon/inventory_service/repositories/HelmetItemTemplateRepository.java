package dev.zymixon.inventory_service.repositories;

import dev.zymixon.inventory_service.entities.template.HelmetItemTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelmetItemTemplateRepository extends JpaRepository<HelmetItemTemplate, Integer> {
}
