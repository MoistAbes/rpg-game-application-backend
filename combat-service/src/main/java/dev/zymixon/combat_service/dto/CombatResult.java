package dev.zymixon.combat_service.dto;

import dev.zymixon.combat_service.models.CombatRequestDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CombatResult {

    private boolean success;
    List<CombatLog> combatLogs;
    private int experience;
    private List<Long> droppedItems;
}
