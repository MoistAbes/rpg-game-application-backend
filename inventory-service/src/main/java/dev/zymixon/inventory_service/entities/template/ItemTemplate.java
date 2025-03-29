package dev.zymixon.inventory_service.entities.template;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Can be JOINED or TABLE_PER_CLASS
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class ItemTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String iconPath;
    private boolean isEnemyDrop; // ✅ Indicates if this item can drop from enemies
    private boolean isStackable;

    @Column(nullable = false, columnDefinition = "float default 0.0")
    private float dropChance;


    public String getItemType() {
        return this.getClass().getSimpleName();  // "NotUsableItem" for example
    }


}
