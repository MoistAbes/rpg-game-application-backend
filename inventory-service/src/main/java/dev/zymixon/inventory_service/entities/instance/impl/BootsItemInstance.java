package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.BootsItemTemplate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("BOOTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BootsItemInstance extends ArmorItemInstance {

    @ManyToOne
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
