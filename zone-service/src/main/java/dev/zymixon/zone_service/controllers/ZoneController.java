package dev.zymixon.zone_service.controllers;

import dev.zymixon.zone_service.dto.LocationDto;
import dev.zymixon.zone_service.dto.ZoneDto;
import dev.zymixon.zone_service.entities.Location;
import dev.zymixon.zone_service.entities.Zone;
import dev.zymixon.zone_service.mappers.ZoneMapper;
import dev.zymixon.zone_service.services.ZoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zone-service/zones")
public class ZoneController {

    private final ZoneService zoneService;

    private static final Logger logger = LoggerFactory.getLogger(ZoneController.class);

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ZoneDto>> getAllZones() {
        logger.info("/zone-service/zones/get-all");

        List<Zone> zones = zoneService.getAllZones();
//        System.out.println("Fetched zones: " + zones);


        for (Zone zone : zones) {
            if(!zone.getLocations().isEmpty()) {
                for (Location location : zone.getLocations()) {
                    System.out.println("Location minEnemyLevel: " + location.getMinEnemyLevel());
                    System.out.println("Location maxEnemyLevel: " + location.getMaxEnemyLevel());
                }
            }
        }

        List<ZoneDto> mappedZones = ZoneMapper.INSTANCE.zonesToZoneDtos(zones);
//        System.out.println("MAPPED ZONES: " + mappedZones);

        for (ZoneDto zone : mappedZones) {
            if(!zone.getLocations().isEmpty()) {
                for (LocationDto location : zone.getLocations()) {
                    System.out.println("Mapped Location minEnemyLevel: " + location.getMinEnemyLevel());
                    System.out.println("Mapped Location maxEnemyLevel: " + location.getMaxEnemyLevel());
                }
            }
        }
        return ResponseEntity.ok(mappedZones);
    }

}
