package dev.zymixon.zone_service.services;

import dev.zymixon.zone_service.entities.Zone;
import dev.zymixon.zone_service.repositories.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneService(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    public List<Zone> getAllZones() {
        return zoneRepository.findAllZones();
    }
}
