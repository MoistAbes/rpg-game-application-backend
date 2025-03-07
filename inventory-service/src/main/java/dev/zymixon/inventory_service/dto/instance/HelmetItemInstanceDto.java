package dev.zymixon.inventory_service.dto.instance;

import dev.zymixon.inventory_service.dto.template.HelmetItemTemplateDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class HelmetItemInstanceDto extends ArmorItemInstanceDto{

    private HelmetItemTemplateDto helmetTemplate;

    @Override
    public String toString() {
        return "HelmetItemInstanceDto{" +
                "helmetTemplate=" + helmetTemplate +
                " " + super.toString() +
                ", type='" + type + '\'' +
                '}';
    }
}
