package dev.zymixon.zone_service.repositories;

import dev.zymixon.zone_service.entities.LocationInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationInstanceRepository extends JpaRepository<LocationInstance, Long> {

    @Query("SELECT li FROM LocationInstance li WHERE li.characterId = :characterId")
    LocationInstance getLocationInstanceByCharacterId(@Param("characterId") Long characterId);
}
