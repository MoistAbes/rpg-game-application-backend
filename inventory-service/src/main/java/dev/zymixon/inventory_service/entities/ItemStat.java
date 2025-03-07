package dev.zymixon.inventory_service.entities;

import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.enums.ItemStatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemStatType statType; // Type of stat (health, armor, etc.)

    private double value; // Can store both integers (e.g., 50 HP) and decimals (e.g., 0.15 for 15% Crit Chance)


    @ManyToOne
    @JoinColumn(name = "item_instance_id")
    private ItemInstance itemInstance;
}
