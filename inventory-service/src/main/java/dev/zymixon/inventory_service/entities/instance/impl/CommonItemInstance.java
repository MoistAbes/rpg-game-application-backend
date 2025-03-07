package dev.zymixon.inventory_service.entities.instance.impl;

import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.template.CommonItemTemplate;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("COMMON")
@Getter
@Setter
public class CommonItemInstance extends ItemInstance {

    @OneToOne
    @JoinColumn(name = "common_item_template_id")
    private CommonItemTemplate commonItemTemplate;

}
