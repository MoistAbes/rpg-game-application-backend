package dev.zymixon.inventory_service.services.item_generation;

import dev.zymixon.inventory_service.entities.ItemStat;
import dev.zymixon.inventory_service.entities.instance.impl.*;
import dev.zymixon.inventory_service.entities.template.*;
import dev.zymixon.inventory_service.enums.ArmorType;
import dev.zymixon.inventory_service.enums.ItemRarity;
import dev.zymixon.inventory_service.models.ItemDropGenerateRequest;
import dev.zymixon.inventory_service.repositories.ItemTemplateRepository;
import dev.zymixon.inventory_service.repositories.item_instance.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ItemGenerationService {

    private final ItemTemplateRepository itemTemplateRepository;
    private final HelmetItemInstanceRepository helmetItemInstanceRepository;
    private final ChestItemInstanceRepository chestItemInstanceRepository;
    private final GlovesItemInstanceRepository glovesItemInstanceRepository;
    private final BootsItemInstanceRepository bootsItemInstanceRepository;
    private final SwordItemInstanceRepository swordItemInstanceRepository;
    private final AxeItemInstanceRepository axeItemInstanceRepository;
    private final CommonItemInstanceRepository commonItemInstanceRepository;

    public ItemGenerationService(ItemTemplateRepository itemTemplateRepository, HelmetItemInstanceRepository helmetItemInstanceRepository, ChestItemInstanceRepository chestItemInstanceRepository, GlovesItemInstanceRepository glovesItemInstanceRepository, BootsItemInstanceRepository bootsItemInstanceRepository, SwordItemInstanceRepository swordItemInstanceRepository, AxeItemInstanceRepository axeItemInstanceRepository, CommonItemInstanceRepository commonItemInstanceRepository) {
        this.itemTemplateRepository = itemTemplateRepository;
        this.helmetItemInstanceRepository = helmetItemInstanceRepository;
        this.chestItemInstanceRepository = chestItemInstanceRepository;
        this.glovesItemInstanceRepository = glovesItemInstanceRepository;
        this.bootsItemInstanceRepository = bootsItemInstanceRepository;
        this.swordItemInstanceRepository = swordItemInstanceRepository;
        this.axeItemInstanceRepository = axeItemInstanceRepository;
        this.commonItemInstanceRepository = commonItemInstanceRepository;
    }

    public boolean generateCombatItemDrops(ItemDropGenerateRequest itemDropGenerateRequest) {

        //ToDO
        //Zeby item stworzyc potrzebne bedzie nam level przeciwnika


        //ToDO trzeba bedzie przemyslec jak zrobic szanse na drop itd.




        List<ItemTemplate> itemTemplates = itemTemplateRepository.findAllByIsEnemyDrop();
        System.out.println("POSSIBLE ITEM DROPS");
        for (ItemTemplate itemTemplate : itemTemplates) {
            System.out.println(itemTemplate.getName());
        }

        List<ItemTemplate> droppedItems = getDroppedItems(itemTemplates, 3, 0.5f, 0.7f);

        System.out.println("DROPPED ITEMS");
        for (ItemTemplate itemTemplate : droppedItems) {
            System.out.println(itemTemplate.getName() + " type: " + itemTemplate.getItemType());
        }
        //ToDO zrobic tera generacje instancji itema jak juz mamy template

        for (ItemTemplate droppedItem : droppedItems) {
            generateItemInstance(droppedItem, itemDropGenerateRequest.getEnemyLevel());
        }




        return true;


    }

    private void generateItemInstance(ItemTemplate droppedItem, int enemyLevel) {

        String itemType = droppedItem.getItemType();

        switch (itemType) {
            case "HelmetItemTemplate":
                generateHelmetItem((HelmetItemTemplate) droppedItem, enemyLevel);
                break;
            case "ChestItemTemplate":
                generateChestItem((ChestItemTemplate) droppedItem, enemyLevel);
                break;
            case "GlovesItemTemplate":
                generateGlovesItem((GlovesItemTemplate) droppedItem, enemyLevel);
                break;
            case "BootsItemTemplate":
                generateBootsItem((BootsItemTemplate) droppedItem, enemyLevel);
                break;
            case "SwordItemTemplate":
                generateSwordItem((SwordItemTemplate) droppedItem, enemyLevel);
                break;
            case "AxeItemTemplate":
                generateAxeItem((AxeItemTemplate) droppedItem, enemyLevel);
                break;
            case "CommonItemTemplate":
                generateCommonItem((CommonItemTemplate) droppedItem);
                break;
            default:
                System.out.println("Item type not known: " + itemType); // Proper statement
                break;
        }

    }




    private List<ItemTemplate> getDroppedItems(List<ItemTemplate> itemTemplates, int maxDrops, float dropPenaltyFactor, float baseDropChance) {
        Random random = new Random();

        List<ItemTemplate> droppedItems = new ArrayList<>();
        float dropMultiplier = 1.0f; // Start at full drop chance

        for (int i = 0; i < maxDrops; i++) {
            // **New Check:** Roll for a drop chance before selecting an item
            if (random.nextFloat() > baseDropChance * dropMultiplier) {
                break; // Stop dropping if we fail the drop chance roll
            }

            // Calculate total weight of remaining items
            float totalWeight = 0;
            for (ItemTemplate item : itemTemplates) {
                totalWeight += item.getDropChance() * dropMultiplier;
            }

            if (totalWeight <= 0) {
                break; // No items left to drop
            }

            // Roll for a random item based on weighted chance
            float randomValue = random.nextFloat() * totalWeight;
            float cumulativeWeight = 0;

            for (ItemTemplate item : itemTemplates) {
                cumulativeWeight += item.getDropChance() * dropMultiplier;
                if (randomValue < cumulativeWeight) {
                    droppedItems.add(item);
                    dropMultiplier *= dropPenaltyFactor; // Reduce chance for next drop
                    break;
                }
            }
        }

        return droppedItems;
    }


    private HelmetItemInstance generateHelmetItem(HelmetItemTemplate itemTemplate, int enemyLevel) {

        ItemRarity generatedItemRarity = ItemPropertiesGeneratorService.generateItemRarity();
        ArmorType generatedItemArmorType = ItemPropertiesGeneratorService.generateItemArmorType();
        int generatedItemQuality = ItemPropertiesGeneratorService.generateItemQuality();
        int generateArmorValue = ItemPropertiesGeneratorService.generateArmorValue(enemyLevel);


        HelmetItemInstance generatedHelmetItemInstance = HelmetItemInstance.builder()
                .itemRarity(generatedItemRarity)
                .quantity(1)
                .armorType(generatedItemArmorType)
                .quality(generatedItemQuality)
                .armorValue(generateArmorValue)
                .levelRequirement(enemyLevel)
                .helmetTemplate(itemTemplate)
                .build();

        generatedHelmetItemInstance.setStats(ItemPropertiesGeneratorService.generateItemStats(generatedItemRarity, generatedHelmetItemInstance));


        System.out.println("GENERATED HELMET ITEM");
        System.out.println(generatedHelmetItemInstance);

        generatedHelmetItemInstance = helmetItemInstanceRepository.save(generatedHelmetItemInstance);

        System.out.println("SAVED HELMET ITEM");
        System.out.println(generatedHelmetItemInstance);

        return generatedHelmetItemInstance;
    }

    private ChestItemInstance generateChestItem(ChestItemTemplate itemTemplate, int enemyLevel) {

        ItemRarity generatedItemRarity = ItemPropertiesGeneratorService.generateItemRarity();
        ArmorType generatedItemArmorType = ItemPropertiesGeneratorService.generateItemArmorType();
        int generatedItemQuality = ItemPropertiesGeneratorService.generateItemQuality();
        int generateArmorValue = ItemPropertiesGeneratorService.generateArmorValue(enemyLevel);


        ChestItemInstance generatedChestItemInstance = ChestItemInstance.builder()
                .itemRarity(generatedItemRarity)
                .quantity(1)
                .armorType(generatedItemArmorType)
                .quality(generatedItemQuality)
                .armorValue(generateArmorValue)
                .levelRequirement(enemyLevel)
                .chestTemplate(itemTemplate)
                .build();

        generatedChestItemInstance.setStats(ItemPropertiesGeneratorService.generateItemStats(generatedItemRarity, generatedChestItemInstance));


        System.out.println("GENERATED CHEST ITEM");
        System.out.println(generatedChestItemInstance);

        generatedChestItemInstance = chestItemInstanceRepository.save(generatedChestItemInstance);

        System.out.println("SAVED CHEST ITEM");
        System.out.println(generatedChestItemInstance);

        return generatedChestItemInstance;
    }

    private GlovesItemInstance generateGlovesItem(GlovesItemTemplate itemTemplate, int enemyLevel) {
        ItemRarity generatedItemRarity = ItemPropertiesGeneratorService.generateItemRarity();
        ArmorType generatedItemArmorType = ItemPropertiesGeneratorService.generateItemArmorType();
        int generatedItemQuality = ItemPropertiesGeneratorService.generateItemQuality();
        int generateArmorValue = ItemPropertiesGeneratorService.generateArmorValue(enemyLevel);


        GlovesItemInstance generatedGlovesItemInstance = GlovesItemInstance.builder()
                .itemRarity(generatedItemRarity)
                .quantity(1)
                .armorType(generatedItemArmorType)
                .quality(generatedItemQuality)
                .armorValue(generateArmorValue)
                .levelRequirement(enemyLevel)
                .glovesTemplate(itemTemplate)
                .build();

        generatedGlovesItemInstance.setStats(ItemPropertiesGeneratorService.generateItemStats(generatedItemRarity, generatedGlovesItemInstance));


        System.out.println("GENERATED GLOVES ITEM");
        System.out.println(generatedGlovesItemInstance);

        generatedGlovesItemInstance = glovesItemInstanceRepository.save(generatedGlovesItemInstance);

        System.out.println("SAVED GLOVES ITEM");
        System.out.println(generatedGlovesItemInstance);

        return generatedGlovesItemInstance;

    }

    private BootsItemInstance generateBootsItem(BootsItemTemplate itemTemplate, int enemyLevel) {
        ItemRarity generatedItemRarity = ItemPropertiesGeneratorService.generateItemRarity();
        ArmorType generatedItemArmorType = ItemPropertiesGeneratorService.generateItemArmorType();
        int generatedItemQuality = ItemPropertiesGeneratorService.generateItemQuality();
        int generateArmorValue = ItemPropertiesGeneratorService.generateArmorValue(enemyLevel);


        BootsItemInstance generatedBootsItemInstance = BootsItemInstance.builder()
                .itemRarity(generatedItemRarity)
                .quantity(1)
                .armorType(generatedItemArmorType)
                .quality(generatedItemQuality)
                .armorValue(generateArmorValue)
                .levelRequirement(enemyLevel)
                .bootsTemplate(itemTemplate)
                .build();

        generatedBootsItemInstance.setStats(ItemPropertiesGeneratorService.generateItemStats(generatedItemRarity, generatedBootsItemInstance));

        System.out.println("GENERATED Boots ITEM");
        System.out.println(generatedBootsItemInstance);

        generatedBootsItemInstance = bootsItemInstanceRepository.save(generatedBootsItemInstance);

        System.out.println("SAVED Boots ITEM");
        System.out.println(generatedBootsItemInstance);

        return generatedBootsItemInstance;

    }

    private SwordItemInstance generateSwordItem(SwordItemTemplate itemTemplate, int enemyLevel) {
        ItemRarity generatedItemRarity = ItemPropertiesGeneratorService.generateItemRarity();
        int generatedItemQuality = ItemPropertiesGeneratorService.generateItemQuality();

        SwordItemInstance swordItemInstance = SwordItemInstance.builder()
                .itemRarity(generatedItemRarity)
                .quantity(1)
                .quality(generatedItemQuality)
                .damageValue(ItemPropertiesGeneratorService.generateDamageValue(enemyLevel))
                .levelRequirement(enemyLevel)
                .swordTemplate(itemTemplate)
                .build();

        swordItemInstance.setStats(ItemPropertiesGeneratorService.generateItemStats(generatedItemRarity, swordItemInstance));

        System.out.println("GENERATED SWORD ITEM");
        System.out.println(swordItemInstance);

        swordItemInstance = swordItemInstanceRepository.save(swordItemInstance);

        System.out.println("SAVED SWORD ITEM");
        System.out.println(swordItemInstance);

        return swordItemInstance;
    }

    private AxeItemInstance generateAxeItem(AxeItemTemplate itemTemplate, int enemyLevel) {
        ItemRarity generatedItemRarity = ItemPropertiesGeneratorService.generateItemRarity();
        int generatedItemQuality = ItemPropertiesGeneratorService.generateItemQuality();

        AxeItemInstance axeItemInstance = AxeItemInstance.builder()
                .itemRarity(generatedItemRarity)
                .quantity(1)
                .quality(generatedItemQuality)
                .damageValue(ItemPropertiesGeneratorService.generateDamageValue(enemyLevel))
                .levelRequirement(enemyLevel)
                .axeTemplate(itemTemplate)
                .build();

        axeItemInstance.setStats(ItemPropertiesGeneratorService.generateItemStats(generatedItemRarity, axeItemInstance));

        System.out.println("GENERATED AXE ITEM");
        System.out.println(axeItemInstance);

        axeItemInstance = axeItemInstanceRepository.save(axeItemInstance);

        System.out.println("SAVED AXE ITEM");
        System.out.println(axeItemInstance);

        return axeItemInstance;
    }

    private CommonItemInstance generateCommonItem(CommonItemTemplate itemTemplate) {

        CommonItemInstance commonItemInstance = CommonItemInstance.builder()
                .itemRarity(ItemRarity.COMMON)
                .quantity(1)
                .commonItemTemplate(itemTemplate)
                .build();

        System.out.println("GENERATED COMMON ITEM");
        System.out.println(commonItemInstance);

        commonItemInstance = commonItemInstanceRepository.save(commonItemInstance);

        System.out.println("SAVED COMMON ITEM");
        System.out.println(commonItemInstance);

        return commonItemInstance;

    }

}
