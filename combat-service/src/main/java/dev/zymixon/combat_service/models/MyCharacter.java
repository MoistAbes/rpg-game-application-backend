package dev.zymixon.combat_service.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyCharacter {

    private Long id;
    private String name;
    private int level;
    private int experience;
    private int nextLevelExperience;
    private int goldAmount;

    private CharacterStats characterStats;

    @Override
    public String toString() {
        return "MyCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", nextLevelExperience=" + nextLevelExperience +
                ", goldAmount=" + goldAmount +
                ", characterStats=" + characterStats +
                '}';
    }
}
