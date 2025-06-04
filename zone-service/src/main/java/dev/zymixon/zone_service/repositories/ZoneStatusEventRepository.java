package dev.zymixon.zone_service.repositories;

import dev.zymixon.zone_service.entities.ZoneStatusEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneStatusEventRepository extends JpaRepository<ZoneStatusEvent, Long> {

    @Query("SELECT z.id from ZoneStatusEvent z")
    List<Long> findAllZoneStatusesId();

}
