package dev.zymixon.character_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int level;
    private int experience;
    private Long userId; // Reference to UserInfo in user-service
    private Long goldAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "character_stats_id")
    private CharacterStats characterStats;

    @Override
    public String toString() {
        return "MyCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", userId=" + userId +
                ", goldAmount=" + goldAmount +
                ", character stats=" + characterStats +
                '}';
    }
}
