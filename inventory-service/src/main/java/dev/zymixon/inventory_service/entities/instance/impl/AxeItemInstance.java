package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.AxeItemTemplate;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("AXE")
@Getter
@Setter
public class AxeItemInstance extends WeaponItemInstance {

    //ToDO przemyslec jak zrobić z templatem bo chyba do zmiany jest w armorach
    @OneToOne
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
