package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.ChestItemTemplate;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CHEST")
@Getter
@Setter
public class ChestItemInstance extends ArmorItemInstance {

    @OneToOne
    @JoinColumn(name = "chest_template_id")
    private ChestItemTemplate chestTemplate;


    @Override
    public String toString() {
        return "ChestItemInstance{" +
                super.toString() +
                "chestTemplate=" + chestTemplate +
                '}';
    }
}
