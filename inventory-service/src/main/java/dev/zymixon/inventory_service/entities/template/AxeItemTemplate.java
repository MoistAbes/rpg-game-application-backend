package dev.zymixon.inventory_service.entities.template;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class AxeItemTemplate extends WeaponItemTemplate{

    @Override
    public String toString() {
        return "AxeItemTemplate{}";
    }

}
