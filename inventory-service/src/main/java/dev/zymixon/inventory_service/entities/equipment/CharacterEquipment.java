package dev.zymixon.inventory_service.entities.equipment;

import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterEquipment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long characterId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "helmet_item_id")
    private ItemInstance helmet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chest_item_id")
    private ItemInstance chest;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gloves_item_id")
    private ItemInstance gloves;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boots_item_id")
    private ItemInstance boots;



}
