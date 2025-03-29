package dev.zymixon.inventory_service.entities.instance;

import dev.zymixon.inventory_service.entities.ItemStat;
import dev.zymixon.inventory_service.enums.ItemRarity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ItemInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private ItemRarity itemRarity;

    @OneToMany(mappedBy = "itemInstance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemStat> stats = new ArrayList<>();

    public String getItemType() {
        return this.getClass().getSimpleName(); // Returns the class name of the actual instance
    }

}
