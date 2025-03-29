package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.template.CommonItemTemplate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("COMMON")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class CommonItemInstance extends ItemInstance {

    @ManyToOne
    @JoinColumn(name = "common_item_template_id")
    private CommonItemTemplate commonItemTemplate;

}
