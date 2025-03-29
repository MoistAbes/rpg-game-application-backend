package dev.zymixon.enemy_service.controllers;

import dev.zymixon.enemy_service.dto.EnemyInstanceDto;
import dev.zymixon.enemy_service.entities.EnemyInstance;
import dev.zymixon.enemy_service.mappers.EnemyInstanceMapper;
import dev.zymixon.enemy_service.models.GenerateEnemyRequest;
import dev.zymixon.enemy_service.services.EnemyInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enemy-service/enemy-instance")
public class EnemyInstanceController {

    private final EnemyInstanceService enemyInstanceService;

    private static final Logger logger = LoggerFactory.getLogger(EnemyInstanceController.class);


    public EnemyInstanceController(EnemyInstanceService enemyInstanceService) {
        this.enemyInstanceService = enemyInstanceService;
    }


//    @PostMapping("/generate-enemy-instance/{enemyTemplateId}")
//    public ResponseEntity<EnemyInstanceDto> generateEnemyInstance(@PathVariable Long enemyTemplateId) {
//        logger.info("/enemy-service/enemy-instance/generate-enemy-instance/{}", enemyTemplateId);
//        EnemyInstance enemyInstance = enemyInstanceService.generateEnemyInstance(enemyTemplateId);
//        System.out.println("ENEMY INSTANCE: " + enemyInstance);
//        EnemyInstanceDto mappedEnemyInstance = EnemyInstanceMapper.INSTANCE.toDto(enemyInstance);
//        System.out.println("ENEMY MAPPED INSTANCE: " + mappedEnemyInstance);
//
//
//        return ResponseEntity.ok(mappedEnemyInstance);
//
//    }

    @PostMapping("/generate-enemy-instance")
    public ResponseEntity<EnemyInstanceDto> generateEnemyInstance(@RequestBody GenerateEnemyRequest generateEnemyRequest) {
        logger.info("/enemy-service/enemy-instance/generate-enemy-instance");
        System.out.println("generateEnemyRequest: " + generateEnemyRequest);
        EnemyInstance enemyInstance = enemyInstanceService.generateEnemyInstance(generateEnemyRequest);
        EnemyInstanceDto mappedEnemyInstance = EnemyInstanceMapper.INSTANCE.toDto(enemyInstance);


        return ResponseEntity.ok(mappedEnemyInstance);

    }

}
