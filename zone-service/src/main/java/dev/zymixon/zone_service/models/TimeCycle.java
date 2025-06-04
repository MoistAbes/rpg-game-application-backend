package dev.zymixon.zone_service.models;

import dev.zymixon.zone_service.enums.TimeOfDay;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TimeCycle {

    private TimeOfDay currentTimeOfDay = TimeOfDay.DAY;
    private Instant lastChangeTimestamp = Instant.now();

}
