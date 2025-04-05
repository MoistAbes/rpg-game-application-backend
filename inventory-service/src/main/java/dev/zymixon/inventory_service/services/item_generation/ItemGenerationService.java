package dev.zymixon.inventory_service.services.item_generation;

import dev.zymixon.inventory_service.entities.ItemStat;
import dev.zymixon.inventory_service.entities.instance.impl.*;
import dev.zymixon.inventory_service.entities.template.*;
import dev.zymixon.inventory_service.enums.ArmorType;
import dev.zymixon.inventory_service.enums.ItemRarity;
import dev.zymixon.inventory_service.models.ItemDropGenerateRequest;
import dev.zymixon.inventory_service.repositories.ItemTemplateRepository;
import dev.zymixon.inventory_service.repositories.item_instance.*;
import dev.zymixon.inventory_service.services.InventorySlotService;
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
    private final InventorySlotService inventorySlotService;

    public ItemGenerationService(ItemTemplateRepository itemTemplateRepository, HelmetItemInstanceRepository helmetItemInstanceRepository, ChestItemInstanceRepository chestItemInstanceRepository, GlovesItemInstanceRepository glovesItemInstanceRepository, BootsItemInstanceRepository bootsItemInstanceRepository, SwordItemInstanceRepository swordItemInstanceRepository, AxeItemInstanceRepository axeItemInstanceRepository, CommonItemInstanceRepository commonItemInstanceRepository, InventorySlotService inventorySlotService) {
        this.itemTemplateRepository = itemTemplateRepository;
        this.helmetItemInstanceRepository = helmetItemInstanceRepository;
        this.chestItemInstanceRepository = chestItemInstanceRepository;
        this.glovesItemInstanceRepository = glovesItemInstanceRepository;
        this.bootsItemInstanceRepository = bootsItemInstanceRepository;
        this.swordItemInstanceRepository = swordItemInstanceRepository;
        this.axeItemInstanceRepository = axeItemInstanceRepository;
        this.commonItemInstanceRepository = commonItemInstanceRepository;
        this.inventorySlotService = inventorySlotService;
    }

    public List<Long> generateCombatItemDrops(ItemDropGenerateRequest itemDropGenerateRequest) {

        List<ItemTemplate> itemTemplates = itemTemplateRepository.findAllByIsEnemyDrop();
        List<ItemTemplate> droppedItems = getDroppedItems(itemTemplates, 3, 0.5f, 0.7f);
        List<Long> droppedItemIds = new ArrayList<>();

        System.out.println("DROPPED ITEMS");
        for (ItemTemplate itemTemplate : droppedItems) {
            System.out.println(itemTemplate.getName() + " type: " + itemTemplate.getItemType());
        }

        for (ItemTemplate droppedItem : droppedItems) {
            droppedItemIds.add(generateItemInstance(droppedItem, itemDropGenerateRequest.getEnemyLevel()));
        }


        //metoda doda do wolnych slotow dropniete itemy a te na które nie ma miejsca usunie
        inventorySlotService.handleSavingDroppedItems(itemDropGenerateRequest, droppedItemIds);

        return droppedItemIds;
    }

    private Long generateItemInstance(ItemTemplate droppedItem, int enemyLevel) {

        String itemType = droppedItem.getItemType();

        return switch (itemType) {
            case "HelmetItemTemplate" -> generateHelmetItem((HelmetItemTemplate) droppedItem, enemyLevel);
            case "ChestItemTemplate" -> generateChestItem((ChestItemTemplate) droppedItem, enemyLevel);
            case "GlovesItemTemplate" -> generateGlovesItem((GlovesItemTemplate) droppedItem, enemyLevel);
            case "BootsItemTemplate" -> generateBootsItem((BootsItemTemplate) droppedItem, enemyLevel);
            case "SwordItemTemplate" -> generateSwordItem((SwordItemTemplate) droppedItem, enemyLevel);
            case "AxeItemTemplate" -> generateAxeItem((AxeItemTemplate) droppedItem, enemyLevel);
            case "CommonItemTemplate" -> generateCommonItem((CommonItemTemplate) droppedItem);
            default -> {
                System.out.println("Item type not known: " + itemType); // Proper statement
                yield 0L;
            }
        };

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


    private Long generateHelmetItem(HelmetItemTemplate itemTemplate, int enemyLevel) {

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

        return generatedHelmetItemInstance.getId();
    }

    private Long generateChestItem(ChestItemTemplate itemTemplate, int enemyLevel) {

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

        return generatedChestItemInstance.getId();
    }

    private Long generateGlovesItem(GlovesItemTemplate itemTemplate, int enemyLevel) {
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

        return generatedGlovesItemInstance.getId();

    }

    private Long generateBootsItem(BootsItemTemplate itemTemplate, int enemyLevel) {
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

        return generatedBootsItemInstance.getId();

    }

    private Long generateSwordItem(SwordItemTemplate itemTemplate, int enemyLevel) {
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

        return swordItemInstance.getId();
    }

    private Long generateAxeItem(AxeItemTemplate itemTemplate, int enemyLevel) {
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

        return axeItemInstance.getId();
    }

    private Long generateCommonItem(CommonItemTemplate itemTemplate) {

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

        return commonItemInstance.getId();

    }

}
