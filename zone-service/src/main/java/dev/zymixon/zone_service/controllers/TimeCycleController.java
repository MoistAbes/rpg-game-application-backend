package dev.zymixon.zone_service.controllers;

import dev.zymixon.zone_service.models.TimeCycle;
import dev.zymixon.zone_service.scheduler.TimeCycleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zone-service/time-cycle")
public class TimeCycleController {

    private final TimeCycleService timeCycleService;

    private static final Logger logger = LoggerFactory.getLogger(TimeCycleController.class);


    public TimeCycleController(TimeCycleService timeCycleService) {
        this.timeCycleService = timeCycleService;
    }


    @GetMapping()
    public ResponseEntity<TimeCycle> getTimeCycle() {
        logger.info("/time-cycle");
        return ResponseEntity.ok(timeCycleService.getTimeCycle());
    }

}
