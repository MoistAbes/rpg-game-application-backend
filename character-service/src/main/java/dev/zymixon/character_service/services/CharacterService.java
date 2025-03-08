package dev.zymixon.character_service.services;

import dev.zymixon.character_service.dto.EquipmentChangeDto;
import dev.zymixon.character_service.dto.EquipmentWeaponChangeDto;
import dev.zymixon.character_service.dto.ItemStatDto;
import dev.zymixon.character_service.entities.CharacterStats;
import dev.zymixon.character_service.entities.MyCharacter;
import dev.zymixon.character_service.exceptions.CharacterNotFoundException;
import dev.zymixon.character_service.model.ItemStatType;
import dev.zymixon.character_service.repositories.CharacterRepository;
import dev.zymixon.character_service.repositories.CharacterStatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterStatsRepository characterStatsRepository;
    private static final Logger logger = LoggerFactory.getLogger(CharacterService.class);


    public CharacterService(CharacterRepository characterRepository, CharacterStatsRepository characterStatsRepository) {
        this.characterRepository = characterRepository;
        this.characterStatsRepository = characterStatsRepository;
    }

    public MyCharacter createCharacter(String name, Long userId) {
        //ToDo
        //dodac zabezpieczenie zeby nie można było utworzyc 2 postaci o tych samych nazwach
        return characterRepository.save(CharacterCreatorUtil.generateCharacter(name, userId));
    }

    public List<MyCharacter> getCharactersByUserId(Long userId) {
        return characterRepository.findAllByUserId(userId);
    }

    public MyCharacter getCharacterById(Long characterId) {
        return characterRepository.findById(characterId)
                .orElseThrow(() -> new CharacterNotFoundException("Character not found with id: " + characterId));
    }


    public boolean calculateCharacterStats(EquipmentChangeDto equipmentChangeDto) {

        MyCharacter character = getCharacterById(equipmentChangeDto.getCharacterId());

        if (equipmentChangeDto.getNewItemStats() == null) {
            //we are removing item
            System.out.println("unequiping item");
            characterStatsRepository.save(handleItemUnequip(character, equipmentChangeDto));
            return true;
        }else if (equipmentChangeDto.getPrevItemStats() == null) {
            //we are equipping item
            System.out.println("equipping item");
             characterStatsRepository.save(handleItemEquip(character, equipmentChangeDto));
             return true;
        }else {
            //we are swapping item
            System.out.println("swapping items");
            character.setCharacterStats(handleItemUnequip(character, equipmentChangeDto));
            characterStatsRepository.save(handleItemEquip(character, equipmentChangeDto));
            return true;


        }
    }



    private CharacterStats handleItemUnequip(MyCharacter character, EquipmentChangeDto equipmentChangeDto) {

        character.getCharacterStats().setDefense(character.getCharacterStats().getDefense() - equipmentChangeDto.getPrevArmorValue());

        for (ItemStatDto itemStat : equipmentChangeDto.getPrevItemStats()) {
            ItemStatType statType = ItemStatType.valueOf(itemStat.getItemStatType()); // Convert String to Enum
            switch (statType) {
                case BONUS_HEALTH:
                    character.getCharacterStats().setMaxHealth((int) (character.getCharacterStats().getMaxHealth() - itemStat.getValue()));
                    break;
                case BONUS_ARMOR:
                    character.getCharacterStats().setDefense((int) (character.getCharacterStats().getDefense() - itemStat.getValue()));
                    break;
                default:
                    // Handle unexpected cases if needed
                    break;
            }
        }

        return character.getCharacterStats();
    }

    private CharacterStats handleItemEquip(MyCharacter character, EquipmentChangeDto equipmentChangeDto){

        character.getCharacterStats().setDefense(character.getCharacterStats().getDefense() + equipmentChangeDto.getNewArmorValue());

        for (ItemStatDto itemStat : equipmentChangeDto.getNewItemStats()) {
            ItemStatType statType = ItemStatType.valueOf(itemStat.getItemStatType()); // Convert String to Enum
            switch (statType) {
                case BONUS_HEALTH:
                    character.getCharacterStats().setMaxHealth((int) (character.getCharacterStats().getMaxHealth() + itemStat.getValue()));
                    break;
                case BONUS_ARMOR:
                    character.getCharacterStats().setDefense((int) (character.getCharacterStats().getDefense() + itemStat.getValue()));
                    break;
                default:
                    // Handle unexpected cases if needed
                    break;
            }
        }

        return character.getCharacterStats();

    }

    private void handleItemSwap() {
        //ToDO poki co przy swapie po prostu zdejmuje i zakladam nie wiem czy bedzie potrzeba osobnej metody do swapu

    }



//    WEAPON

    public boolean calculateCharacterWeaponStats(EquipmentWeaponChangeDto equipmentWeaponChangeDto) {

        MyCharacter character = getCharacterById(equipmentWeaponChangeDto.getCharacterId());

        if (equipmentWeaponChangeDto.getNewItemStats() == null) {
            //we are removing item
            System.out.println("unequiping weapon item");
            characterStatsRepository.save(handleWeaponItemUnequip(character, equipmentWeaponChangeDto));
            return true;
        }else if (equipmentWeaponChangeDto.getPrevItemStats() == null) {
            //we are equipping item
            System.out.println("equipping weapon item");
            characterStatsRepository.save(handleWeaponItemEquip(character, equipmentWeaponChangeDto));
            return true;
        }else {
            //we are swapping item
            System.out.println("swapping weapon items");
            character.setCharacterStats(handleWeaponItemUnequip(character, equipmentWeaponChangeDto));
            characterStatsRepository.save(handleWeaponItemEquip(character, equipmentWeaponChangeDto));
            return true;


        }
    }

    private CharacterStats handleWeaponItemEquip(MyCharacter character, EquipmentWeaponChangeDto equipmentWeaponChangeDto) {
        character.getCharacterStats().setAttack(character.getCharacterStats().getAttack() + equipmentWeaponChangeDto.getNewDamageValue());

        for (ItemStatDto itemStat : equipmentWeaponChangeDto.getNewItemStats()) {
            ItemStatType statType = ItemStatType.valueOf(itemStat.getItemStatType()); // Convert String to Enum
            switch (statType) {
                case BONUS_HEALTH:
                    character.getCharacterStats().setMaxHealth((int) (character.getCharacterStats().getMaxHealth() + itemStat.getValue()));
                    break;
                case BONUS_ARMOR:
                    character.getCharacterStats().setDefense((int) (character.getCharacterStats().getDefense() + itemStat.getValue()));
                    break;
                default:
                    // Handle unexpected cases if needed
                    break;
            }
        }

        return character.getCharacterStats();
    }

    private CharacterStats handleWeaponItemUnequip(MyCharacter character, EquipmentWeaponChangeDto equipmentWeaponChangeDto){
        character.getCharacterStats().setAttack(character.getCharacterStats().getAttack() - equipmentWeaponChangeDto.getPrevDamageValue());

        for (ItemStatDto itemStat : equipmentWeaponChangeDto.getPrevItemStats()) {
            ItemStatType statType = ItemStatType.valueOf(itemStat.getItemStatType()); // Convert String to Enum
            switch (statType) {
                case BONUS_HEALTH:
                    character.getCharacterStats().setMaxHealth((int) (character.getCharacterStats().getMaxHealth() - itemStat.getValue()));
                    break;
                case BONUS_ARMOR:
                    character.getCharacterStats().setDefense((int) (character.getCharacterStats().getDefense() - itemStat.getValue()));
                    break;
                default:
                    // Handle unexpected cases if needed
                    break;
            }
        }

        return character.getCharacterStats();
    }


}
