package dev.zymixon.inventory_service.services.item_generation;

import dev.zymixon.inventory_service.entities.ItemStat;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.entities.template.ItemTemplate;
import dev.zymixon.inventory_service.enums.ArmorType;
import dev.zymixon.inventory_service.enums.ItemRarity;
import dev.zymixon.inventory_service.enums.ItemStatType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ItemPropertiesGeneratorService {

    private static final Random RANDOM = new Random();

    public static ItemRarity generateItemRarity() {
        float roll = RANDOM.nextFloat(); // Random value between 0.0 and 1.0

        if (roll < 0.6) return ItemRarity.COMMON;   // 60% chance
        if (roll < 0.85) return ItemRarity.RARE;    // 25% chance (85 - 60)
        if (roll < 0.97) return ItemRarity.EPIC;    // 12% chance (97 - 85)
        return ItemRarity.LEGENDARY;               // 3% chance (100 - 97)
    }

    public static ArmorType generateItemArmorType() {
        return ArmorType.values()[RANDOM.nextInt(ArmorType.values().length)];
    }



    public static int generateItemQuality() {
        return RANDOM.nextInt(100);
    }


    public static List<ItemStat> generateItemStats(ItemRarity rarity, ItemInstance itemInstance) {

        List<ItemStat> generatedItemStats = new ArrayList<>();

        int bonusStatsAmount = switch (rarity) {
            case COMMON -> 1;
            case RARE -> 2;
            case EPIC -> 3;
            case LEGENDARY -> 4;
        };

      for (int i = 0; i < bonusStatsAmount; i++) {
          ItemStat stat = ItemStat.builder()
                  .statType(generateItemStat())
                  .value(generateItemStatTypeValue())
                  .itemInstance(itemInstance)
                  .build();

          generatedItemStats.add(stat);
      }

      return generatedItemStats;
    }

    private static ItemStatType generateItemStat() {
        return ItemStatType.values()[RANDOM.nextInt(ArmorType.values().length)];

    }

    private static double generateItemStatTypeValue() {
        double value = RANDOM.nextDouble(1, 11); // Generates a number between 1.00 and 10.99
        return Math.round(value * 100.0) / 100.0; // Rounds to 2 decimal places
    }


    public static int generateArmorValue(int enemyLevel) {
        return generateRandomNumberBetween(1, 15) + enemyLevel;
    }

    public static int generateDamageValue(int enemyLevel) {
        return generateRandomNumberBetween(5,15) + enemyLevel;
    }


    private static int generateRandomNumberBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


}
