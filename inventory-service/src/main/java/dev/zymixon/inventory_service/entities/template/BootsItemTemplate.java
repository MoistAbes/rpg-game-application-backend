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
public class BootsItemTemplate extends ItemTemplate {

    private int baseAmountOfBonusStats;


}
