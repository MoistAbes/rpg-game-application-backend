package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.HelmetItemTemplate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@DiscriminatorValue("HELMET")
@Getter
@Setter
public class HelmetItemInstance extends ArmorItemInstance {
    // This can have more properties if needed

    @OneToOne
    @JoinColumn(name = "helmet_template_id")
    private HelmetItemTemplate helmetTemplate;


    @Override
    public String toString() {
        return "HelmetItemInstance{" +
                super.toString() +
                "helmetTemplate=" + helmetTemplate +
                '}';
    }
}
