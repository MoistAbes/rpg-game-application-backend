package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.ChestItemTemplate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("CHEST")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ChestItemInstance extends ArmorItemInstance {

    @ManyToOne
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
