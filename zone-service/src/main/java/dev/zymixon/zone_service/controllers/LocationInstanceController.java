package dev.zymixon.zone_service.controllers;

import dev.zymixon.zone_service.dto.LocationInstanceDto;
import dev.zymixon.zone_service.entities.LocationInstance;
import dev.zymixon.zone_service.mappers.LocationInstanceCustomMapper;
import dev.zymixon.zone_service.services.LocationInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zone-service/location-instance")
public class LocationInstanceController {
    private static final Logger logger = LoggerFactory.getLogger(LocationInstanceController.class);


    private final LocationInstanceService locationInstanceService;
    private final LocationInstanceCustomMapper locationInstanceMapper;

    public LocationInstanceController(LocationInstanceService locationInstanceService, LocationInstanceCustomMapper locationInstanceMapper) {
        this.locationInstanceService = locationInstanceService;
        this.locationInstanceMapper = locationInstanceMapper;
    }

    @GetMapping("/character/{characterId}")
    public ResponseEntity<LocationInstanceDto> getLocationInstanceByCharacterId(@PathVariable Long characterId) {
        logger.info("getLocationInstanceByCharacterId {}", characterId);

        LocationInstance locationInstance = locationInstanceService.getLocationInstanceByCharacterId(characterId);


        LocationInstanceDto mappedLocationInstance = locationInstanceMapper.toDto(locationInstance);
//

        System.out.println("mappedLocationInstance = " + mappedLocationInstance);

        return ResponseEntity.ok(mappedLocationInstance);
    }

    @PostMapping("/create/{locationId}/{characterId}")
    public ResponseEntity<LocationInstanceDto> createLocationInstance(@PathVariable Long locationId, @PathVariable Long characterId) {
        logger.info("createLocationInstance {}", locationId);

        LocationInstance locationInstance = locationInstanceService.createLocationInstance(locationId, characterId);

        if (locationInstance.getLocation().getZone() == null) {
            System.out.println("locationInstance.getLocation().getZone() is null");
        }


        LocationInstanceDto mappedLocationInstance = locationInstanceMapper.toDto(locationInstance);


        System.out.println("generated location instance: " + mappedLocationInstance);

        return ResponseEntity.ok(mappedLocationInstance);
    }

    @DeleteMapping("/delete/{locationInstanceId}")
    public ResponseEntity<Void> deleteLocationInstance(@PathVariable Long locationInstanceId) {
        logger.info("zone-service/location-instance/delete/{}", locationInstanceId);

        locationInstanceService.deleteLocationInstanceById(locationInstanceId);

        return ResponseEntity.ok().build();
    }




}
