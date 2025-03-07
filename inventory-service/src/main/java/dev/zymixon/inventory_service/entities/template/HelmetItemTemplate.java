package dev.zymixon.inventory_service.entities.template;

import jakarta.persistence.Entity;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HelmetItemTemplate extends ItemTemplate {

    private int baseAmountOfBonusStats;


}
