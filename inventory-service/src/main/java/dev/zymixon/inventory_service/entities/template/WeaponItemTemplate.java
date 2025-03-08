package dev.zymixon.inventory_service.entities.template;


import dev.zymixon.inventory_service.enums.WeaponType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeaponItemTemplate extends ItemTemplate {

    protected WeaponType weaponType;

}
