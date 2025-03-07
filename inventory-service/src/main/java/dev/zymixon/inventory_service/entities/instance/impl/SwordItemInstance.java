package dev.zymixon.inventory_service.entities.instance.impl;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("SWORD")
@Getter
@Setter
public class SwordItemInstance extends WeaponItemInstance{

    //ToDO przemyslec jak zrobić z templatem bo chyba do zmiany jest w armorach
}
