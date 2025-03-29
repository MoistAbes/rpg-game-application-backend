package dev.zymixon.inventory_service.entities.template;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("AXE")
@ToString(callSuper = true) // ✅ Includes parent class fields in toString()
public class AxeItemTemplate extends WeaponItemTemplate{



}
