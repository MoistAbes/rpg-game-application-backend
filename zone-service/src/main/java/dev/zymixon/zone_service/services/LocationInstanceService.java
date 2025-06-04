package dev.zymixon.zone_service.services;

import dev.zymixon.zone_service.entities.Location;
import dev.zymixon.zone_service.entities.LocationInstance;
import dev.zymixon.zone_service.repositories.LocationInstanceRepository;
import dev.zymixon.zone_service.repositories.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationInstanceService {

    private final LocationInstanceRepository locationInstanceRepository;
    private final LocationRepository locationRepository;

    public LocationInstanceService(LocationInstanceRepository locationInstanceRepository, LocationRepository locationRepository) {
        this.locationInstanceRepository = locationInstanceRepository;
        this.locationRepository = locationRepository;
    }

    public LocationInstance getLocationInstanceByCharacterId(Long characterId) {

        return locationInstanceRepository.getLocationInstanceByCharacterId(characterId);

    }

    public LocationInstance createLocationInstance(Long locationId, Long characterId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found")); // lub własny wyjątek

        LocationInstance locationInstance = LocationInstance.builder()
                .characterId(characterId)
                .location(location)
                .build();

        return locationInstanceRepository.save(locationInstance);
    }


    public void deleteLocationInstanceById(Long locationInstanceId) {
        locationInstanceRepository.deleteById(locationInstanceId);
    }
}
