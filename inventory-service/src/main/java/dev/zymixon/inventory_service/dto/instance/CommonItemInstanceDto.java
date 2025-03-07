package dev.zymixon.inventory_service.dto.instance;

import dev.zymixon.inventory_service.dto.template.CommonItemTemplateDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CommonItemInstanceDto extends ItemInstanceDto {

    private CommonItemTemplateDto commonItemTemplate;


    @Override
    public String toString() {
        return "CommonItemInstanceDto{" +
                "commonItemTemplate=" + commonItemTemplate +
                ", type='" + type + '\'' +
                '}';
    }
}
