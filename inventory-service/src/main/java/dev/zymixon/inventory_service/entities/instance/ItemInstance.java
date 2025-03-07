package dev.zymixon.inventory_service.entities.instance;

import dev.zymixon.inventory_service.entities.ItemStat;
import dev.zymixon.inventory_service.enums.ItemRarity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @Override
    public String toString() {
        return "ItemInstance{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", itemRarity=" + itemRarity +
                ", type=" + getItemType() +
                '}';
    }

    // Add common properties for all items, like name, description, etc.
}
