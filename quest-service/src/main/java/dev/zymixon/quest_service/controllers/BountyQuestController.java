package dev.zymixon.quest_service.controllers;

import dev.zymixon.quest_service.entities.BountyQuest;
import dev.zymixon.quest_service.services.BountyQuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quest-service/bounty-quest")
public class BountyQuestController {

    private final BountyQuestService bountyQuestService;

    public BountyQuestController(BountyQuestService bountyQuestService) {
        this.bountyQuestService = bountyQuestService;
    }

    @GetMapping("/get-all/{characterId}")
    public ResponseEntity<List<BountyQuest>> getBountyQuests(@PathVariable Long characterId) {

        List<BountyQuest> bountyQuests = bountyQuestService.findAllByCharacterId(characterId);

        return ResponseEntity.ok(bountyQuests);
    }

}
