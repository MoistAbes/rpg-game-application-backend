package dev.zymixon.combat_service.controllers;

import dev.zymixon.combat_service.dto.CombatResult;
import dev.zymixon.combat_service.models.CombatRequestDto;
import dev.zymixon.combat_service.services.CombatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/combat-service/combat")
public class CombatController {

    private static final Logger logger = LoggerFactory.getLogger(CombatController.class);
    private final CombatService combatService;

    public CombatController(CombatService combatService) {
        this.combatService = combatService;
    }


    @PutMapping("/start")
    public ResponseEntity<CombatResult> startCombat(@RequestBody CombatRequestDto combatRequestDto) {
        logger.info("/combat-service/combat/start");
        System.out.println("COMBAT REQUEST DTO: " + combatRequestDto);

        CombatResult combatResult = combatService.calculateCombat(combatRequestDto);

        System.out.println("COMBAT RESULT: " + combatResult);

        return ResponseEntity.ok(combatResult);
    }



}
