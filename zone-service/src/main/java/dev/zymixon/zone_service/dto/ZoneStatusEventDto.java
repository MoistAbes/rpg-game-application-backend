package dev.zymixon.zone_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ZoneStatusEventDto {

    private Long id;
    private String name;
    private String description;

}
