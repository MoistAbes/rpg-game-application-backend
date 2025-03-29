package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.AxeItemTemplate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("AXE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AxeItemInstance extends WeaponItemInstance {

    //ToDO przemyslec jak zrobić z templatem bo chyba do zmiany jest w armorach
    @ManyToOne
    @JoinColumn(name = "axe_template_id")
    private AxeItemTemplate axeTemplate;


    @Override
    public String toString() {
        return "AxeItemTemplate{" +
                super.toString() +
                "axeTemplate=" + axeTemplate +
                '}';
    }
}
