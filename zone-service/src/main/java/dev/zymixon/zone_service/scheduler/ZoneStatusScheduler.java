package dev.zymixon.zone_service.scheduler;

import dev.zymixon.zone_service.entities.Zone;
import dev.zymixon.zone_service.entities.ZoneStatusEvent;
import dev.zymixon.zone_service.repositories.ZoneRepository;
import dev.zymixon.zone_service.repositories.ZoneStatusEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ZoneStatusScheduler {

    private static final long CYCLE_DURATION_MILLIS = 3600000; // 1 hour

    private final ZoneRepository zoneRepository;
    private final ZoneStatusEventRepository zoneStatusRepository;

    // Track previously updated zone IDs
    private final List<Long> previousZoneIds = new ArrayList<>();

    public ZoneStatusScheduler(ZoneRepository zoneRepository, ZoneStatusEventRepository zoneStatusRepository) {
        this.zoneRepository = zoneRepository;
        this.zoneStatusRepository = zoneStatusRepository;
    }

    //zrobione jako transactional zeby uniknac lazy loading exception
    @Transactional
    @Scheduled(fixedRate = CYCLE_DURATION_MILLIS)
    public void setZoneStatus() {
        // Step 1: Clear previous statuses
        clearPreviousStatuses();

        // Step 2: Fetch current zone and status IDs
        List<Long> zoneIds = zoneRepository.findAllZonesId();
        List<Long> zoneStatusIds = zoneStatusRepository.findAllZoneStatusesId();

        if (zoneIds.isEmpty() || zoneStatusIds.isEmpty()) {
            System.out.println("No zones or statuses found.");
            return;
        }

        // Step 3: Randomly select one zone and one status
        Random random = new Random();
        Long randomZoneId = zoneIds.get(random.nextInt(zoneIds.size()));
        Long randomZoneStatusId = zoneStatusIds.get(random.nextInt(zoneStatusIds.size()));

        Zone zone = zoneRepository.findById(randomZoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        ZoneStatusEvent zoneStatus = zoneStatusRepository.findById(randomZoneStatusId)
                .orElseThrow(() -> new RuntimeException("ZoneStatus not found"));

        // Step 4: Assign the new status to the zone
        zone.getStatuses().add(zoneStatus);
        zoneRepository.save(zone);
        System.out.println("Zone " + zone.getName() + " is now assigned status " + zoneStatus.getName());

        // Step 5: Update the list of previously updated zones (clear previous and add current)
        previousZoneIds.clear();
        previousZoneIds.add(randomZoneId);
    }

    private void clearPreviousStatuses() {
        if (previousZoneIds.isEmpty()) {
            return;
        }
        for (Long zoneId : previousZoneIds) {
            Zone zone = zoneRepository.findById(zoneId)
                    .orElse(null);
            if (zone != null) {
                // Option 1: Clear all statuses
                zone.getStatuses().clear();
                // Option 2: Remove only specific statuses (if needed)
                // zone.getStatuses().removeIf(status -> "cursed".equals(status.getName()));
                zoneRepository.save(zone);
                System.out.println("Cleared statuses for zone " + zone.getName());
            }
        }
    }

}
