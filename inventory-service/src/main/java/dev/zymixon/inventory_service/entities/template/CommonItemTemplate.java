package dev.zymixon.inventory_service.entities.template;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@AllArgsConstructor
@Setter
@DiscriminatorValue("COMMON")
@ToString(callSuper = true) // ✅ Includes parent class fields in toString()
@SuperBuilder
public class CommonItemTemplate extends ItemTemplate {





}
