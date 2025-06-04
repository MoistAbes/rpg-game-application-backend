package dev.zymixon.zone_service.scheduler;

import dev.zymixon.zone_service.enums.TimeOfDay;
import dev.zymixon.zone_service.models.TimeCycle;
import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@Getter
public class TimeCycleService {

    private static final long CYCLE_DURATION_MILLIS = 3600000; // 1 hour

    private final TimeCycle timeCycle = new TimeCycle(); // init with default values

    public Duration getTimeUntilNextChange() {
        Instant nextChange = timeCycle.getLastChangeTimestamp().plusMillis(CYCLE_DURATION_MILLIS);
        return Duration.between(Instant.now(), nextChange);
    }

    public Instant getNextChangeTime() {
        return timeCycle.getLastChangeTimestamp().plusMillis(CYCLE_DURATION_MILLIS);
    }

    @Scheduled(fixedRate = CYCLE_DURATION_MILLIS)
    public void toggleTime() {
        TimeOfDay current = timeCycle.getCurrentTimeOfDay();
        TimeOfDay next = (current == TimeOfDay.DAY) ? TimeOfDay.NIGHT : TimeOfDay.DAY;

        timeCycle.setCurrentTimeOfDay(next);
        timeCycle.setLastChangeTimestamp(Instant.now());

        System.out.println("Time changed to: " + next);
    }
}

