package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.SwordItemTemplate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("SWORD")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SwordItemInstance extends WeaponItemInstance{

    //ToDO przemyslec jak zrobić z templatem bo chyba do zmiany jest w armorach
    @ManyToOne
    @JoinColumn(name = "sword_template_id")
    private SwordItemTemplate swordTemplate;


    @Override
    public String toString() {
        return "SwordItemTemplate{" +
                super.toString() +
                "swordTemplate=" + swordTemplate +
                '}';
    }
}
