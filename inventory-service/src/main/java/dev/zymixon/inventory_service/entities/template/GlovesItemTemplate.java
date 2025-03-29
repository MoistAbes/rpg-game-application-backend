package dev.zymixon.inventory_service.entities.template;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("GLOVES")
@ToString(callSuper = true) // ✅ Includes parent class fields in toString()
public class GlovesItemTemplate extends ItemTemplate {

    private int baseAmountOfBonusStats;

}
