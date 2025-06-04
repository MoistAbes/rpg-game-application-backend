package dev.zymixon.zone_service.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LocationInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private Long characterId;

    //ToDO tutaj trzeba bedzie ogarnac czy chcemy juz na starcie tworzenia instancji stworzyc wszystkich przeciwnoów czy generowac ich jeden po drugim
    @ElementCollection
    private List<Long> enemyInstanceId = new ArrayList<>();


}
