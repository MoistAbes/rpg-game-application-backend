package dev.zymixon.inventory_service.dto.template;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class ItemTemplateDto {

    protected Long id;
    protected String name;
    protected String description;
    protected String iconPath;
    protected boolean isEnemyDrop; // ✅ Indicates if this item can drop from enemies
    protected boolean isStackable;



}
