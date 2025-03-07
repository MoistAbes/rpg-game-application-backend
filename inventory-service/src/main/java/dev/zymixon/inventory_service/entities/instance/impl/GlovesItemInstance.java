package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.GlovesItemTemplate;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("GLOVES")
@Getter
@Setter
public class GlovesItemInstance extends ArmorItemInstance {


    @OneToOne
    @JoinColumn(name = "gloves_template_id")
    private GlovesItemTemplate glovesTemplate;


    @Override
    public String toString() {
        return "GlovesItemInstance{" +
                super.toString() +
                "glovesTemplate=" + glovesTemplate +
                '}';
    }

}
