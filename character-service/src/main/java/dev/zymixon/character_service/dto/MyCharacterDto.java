package dev.zymixon.character_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyCharacterDto {

    private Long id;
    private String name;
    private int level;
    private int experience;
    private Long goldAmount;
    private Long nextLevelExperience; // This is extra, so we will map it manually


    // New field for character stats
    private CharacterStatsDto characterStats;

}
