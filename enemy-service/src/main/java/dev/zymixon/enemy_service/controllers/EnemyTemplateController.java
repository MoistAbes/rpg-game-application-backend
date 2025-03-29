package dev.zymixon.enemy_service.controllers;

import dev.zymixon.enemy_service.enums.EnemyTypeEnum;
import dev.zymixon.enemy_service.services.EnemyTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enemy-service/enemy-template")
public class EnemyTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(EnemyTemplateController.class);

    private final EnemyTemplateService enemyTemplateService;

    public EnemyTemplateController(EnemyTemplateService enemyTemplateService) {
        this.enemyTemplateService = enemyTemplateService;
    }

    @GetMapping("/get-all/type/tier")
    public ResponseEntity<List<Long>> getAllEnemyTemplateIdsByTypeAndTier(@RequestParam List<EnemyTypeEnum> enemyTypes, @RequestParam List<Integer> tiers) {
        logger.info("/enemy-service/enemy-template/get-all/type/tier | {} | {}", enemyTypes, tiers);

        return ResponseEntity.ok(enemyTemplateService.getAllEnemyTemplateIdsByRankAndTier(enemyTypes, tiers));
    }
}
