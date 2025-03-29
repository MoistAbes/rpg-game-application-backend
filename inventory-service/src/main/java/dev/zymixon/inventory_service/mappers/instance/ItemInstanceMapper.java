package dev.zymixon.inventory_service.mappers.instance;

import dev.zymixon.inventory_service.dto.instance.ItemInstanceDto;
import dev.zymixon.inventory_service.entities.instance.impl.*;
import dev.zymixon.inventory_service.entities.instance.ItemInstance;
import dev.zymixon.inventory_service.enums.ItemType;
import dev.zymixon.inventory_service.mappers.ItemMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ItemInstanceMapper {

    // Use a map for more scalable mapping
    private final Map<ItemType, ItemMapper> itemMappers;

    public ItemInstanceMapper(CommonItemInstanceMapper commonItemInstanceMapper,
                              HelmetItemInstanceMapper helmetItemInstanceMapper,
                              ChestItemInstanceMapper chestItemInstanceMapper,
                              GlovesItemInstanceMapper glovesItemInstanceMapper,
                              BootsItemInstanceMapper bootsItemInstanceMapper,
                              SwordItemInstanceMapper swordItemInstanceMapper,
                              AxeItemInstanceMapper axeItemInstanceMapper) {

        // Initialize a map of mappers to handle different types of ItemInstances
        itemMappers = Map.of(
                ItemType.COMMON_ITEM_INSTANCE, commonItemInstanceMapper,
                ItemType.HELMET_ITEM_INSTANCE, helmetItemInstanceMapper,
                ItemType.CHEST_ITEM_INSTANCE, chestItemInstanceMapper,
                ItemType.GLOVES_ITEM_INSTANCE, glovesItemInstanceMapper,
                ItemType.BOOTS_ITEM_INSTANCE, bootsItemInstanceMapper,
                ItemType.SWORD_ITEM_INSTANCE, swordItemInstanceMapper,
                ItemType.AXE_ITEM_INSTANCE, axeItemInstanceMapper
        );
    }

    public ItemInstanceDto mapToDto(ItemInstance itemInstance) {
        if (itemInstance == null) {
            return null;
        }

        // Using the class type directly (if you can determine the type in Java)
        ItemType itemType = getItemType(itemInstance);

        // Lookup the appropriate mapper
        ItemMapper itemMapper = itemMappers.get(itemType);

        if (itemMapper != null) {
            return itemMapper.mapToDto(itemInstance);
        } else {
            throw new IllegalStateException("Unknown ItemInstance type: " + itemType);
        }
    }

    private ItemType getItemType(ItemInstance itemInstance) {
        // Determine the type of the ItemInstance dynamically
        return switch (itemInstance) {
            case CommonItemInstance commonItemInstance -> ItemType.COMMON_ITEM_INSTANCE;
            case HelmetItemInstance helmetItemInstance -> ItemType.HELMET_ITEM_INSTANCE;
            case ChestItemInstance chestItemInstance -> ItemType.CHEST_ITEM_INSTANCE;
            case GlovesItemInstance glovesItemInstance -> ItemType.GLOVES_ITEM_INSTANCE;
            case BootsItemInstance bootsItemInstance -> ItemType.BOOTS_ITEM_INSTANCE;
            case SwordItemInstance swordItemInstance -> ItemType.SWORD_ITEM_INSTANCE;
            case AxeItemInstance axeItemInstance -> ItemType.AXE_ITEM_INSTANCE;
            case null, default -> throw new IllegalArgumentException("Unknown item instance type");
        };
    }
}

