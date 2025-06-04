package dev.zymixon.zone_service.services;

import dev.zymixon.zone_service.repositories.ZoneStatusEventRepository;
import org.springframework.stereotype.Service;

@Service
public class ZoneStatusService {

    private final ZoneStatusEventRepository zoneStatusRepository;

    public ZoneStatusService(ZoneStatusEventRepository zoneStatusRepository) {
        this.zoneStatusRepository = zoneStatusRepository;
    }




}
