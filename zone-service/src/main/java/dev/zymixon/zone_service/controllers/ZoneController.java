package dev.zymixon.zone_service.controllers;

import dev.zymixon.zone_service.dto.ZoneDto;
import dev.zymixon.zone_service.entities.Zone;
import dev.zymixon.zone_service.mappers.ZoneCustomMapper;
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
    private final ZoneCustomMapper zoneMapper;

    private static final Logger logger = LoggerFactory.getLogger(ZoneController.class);

    public ZoneController(ZoneService zoneService, ZoneCustomMapper zoneMapper) {
        this.zoneService = zoneService;
        this.zoneMapper = zoneMapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ZoneDto>> getAllZones() {
        logger.info("/zone-service/zones/get-all");

        List<Zone> zones = zoneService.getAllZones();
        List<ZoneDto> mappedZones = zoneMapper.toDtoList(zones);

        return ResponseEntity.ok(mappedZones);
    }

}
