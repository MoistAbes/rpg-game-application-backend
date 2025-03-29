package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.GlovesItemTemplate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("GLOVES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GlovesItemInstance extends ArmorItemInstance {


    @ManyToOne // ✅ Change from OneToOne to ManyToOne
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
