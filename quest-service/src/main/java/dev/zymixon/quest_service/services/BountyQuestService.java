package dev.zymixon.quest_service.services;

import dev.zymixon.quest_service.entities.BountyQuest;
import dev.zymixon.quest_service.repositories.BountyQuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BountyQuestService {


    private final BountyQuestRepository bountyQuestRepository;

    public BountyQuestService(BountyQuestRepository bountyQuestRepository) {
        this.bountyQuestRepository = bountyQuestRepository;
    }


    public List<BountyQuest> findAllByCharacterId(Long characterId) {
        return bountyQuestRepository.findAllByCharacterId(characterId);
    }
}
