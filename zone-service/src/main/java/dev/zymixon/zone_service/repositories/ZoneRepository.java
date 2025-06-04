package dev.zymixon.zone_service.repositories;

import dev.zymixon.zone_service.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    @Query("SELECT z FROM Zone z LEFT JOIN FETCH z.locations l ORDER BY l.position ASC")
    List<Zone> findAllZones();

    @Query("SELECT z.id FROM Zone z")
    List<Long> findAllZonesId();
}
