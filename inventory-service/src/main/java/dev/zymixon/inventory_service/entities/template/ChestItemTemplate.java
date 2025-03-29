package dev.zymixon.inventory_service.entities.template;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CHEST")
@ToString(callSuper = true) // ✅ Includes parent class fields in toString()
public class ChestItemTemplate extends ItemTemplate {

    private int baseAmountOfBonusStats;


}
