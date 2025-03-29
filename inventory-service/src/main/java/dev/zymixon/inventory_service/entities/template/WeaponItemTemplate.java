package dev.zymixon.inventory_service.entities.template;


import dev.zymixon.inventory_service.enums.WeaponType;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true) // ✅ Includes parent class fields in toString()
public class WeaponItemTemplate extends ItemTemplate {

    protected WeaponType weaponType;

}
