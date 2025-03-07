package dev.zymixon.inventory_service.dto.template;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HelmetItemTemplateDto {
    private Long id;
    private String name;
    private String description;
    private String iconPath;
    private boolean isEnemyDrop; // ✅ Indicates if this item can drop from enemies
    private boolean isStackable;

}
