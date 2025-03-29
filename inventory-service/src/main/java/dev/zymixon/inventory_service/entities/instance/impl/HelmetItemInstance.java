package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.template.HelmetItemTemplate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@DiscriminatorValue("HELMET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class HelmetItemInstance extends ArmorItemInstance {
    // This can have more properties if needed

    @ManyToOne // ✅ Change from OneToOne to ManyToOne
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
