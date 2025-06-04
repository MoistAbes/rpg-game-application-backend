package dev.zymixon.enemy_service.controllers;

import dev.zymixon.enemy_service.entities.EnemyType;
import dev.zymixon.enemy_service.services.EnemyTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enemy-service/enemy-type")
public class EnemyTypeController {

    private static final Logger logger = LoggerFactory.getLogger(EnemyTypeController.class);

    private final EnemyTypeService enemyTypeService;


    public EnemyTypeController(EnemyTypeService enemyTypeService) {
        this.enemyTypeService = enemyTypeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<EnemyType>> getAllEnemyTypes() {
        logger.info("/enemy-service/enemy-type/get-all");

        return ResponseEntity.ok(enemyTypeService.getAllEnemyType());
    }
}
