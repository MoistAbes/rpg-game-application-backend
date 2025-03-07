package dev.zymixon.inventory_service.entities.inventory;

import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventorySlot {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_instance_id")  // Renamed to item_instance_id for clarity
    private ItemInstance itemInstance;     // Any

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private CharacterInventory inventory;


    @Override
    public String toString() {
        return "InventorySlot{" +
                "id=" + id +
                ", position=" + position +
                ", itemInstance=" + itemInstance +
                '}';
    }
}
