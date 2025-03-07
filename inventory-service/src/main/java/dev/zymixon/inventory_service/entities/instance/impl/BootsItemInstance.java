package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.BootsItemTemplate;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("BOOTS")
@Getter
@Setter
public class BootsItemInstance extends ArmorItemInstance {

    @OneToOne
    @JoinColumn(name = "boots_template_id")
    private BootsItemTemplate bootsTemplate;

    @Override
    public String toString() {
        return "BootsItemInstance{" +
                super.toString() +
                "bootsTemplate=" + bootsTemplate +
                '}';
    }

}
