package dev.zymixon.inventory_service.entities.template;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChestItemTemplate extends ItemTemplate {

    private int baseAmountOfBonusStats;


}
