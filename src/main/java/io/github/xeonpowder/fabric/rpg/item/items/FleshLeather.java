package io.github.xeonpowder.fabric.rpg.item.items;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.world.loot.ConstantLootTableRange;
import net.minecraft.world.loot.entry.ItemEntry;

/**
 * HumanLeather
 */
public class FleshLeather extends FabricRPGItem {
    public static FleshLeather FLESH_LEATHER;
    static {
        FLESH_LEATHER = new FleshLeather();
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:entity".contains(id.toString())) {
                System.out.println(id.toString());
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(ConstantLootTableRange.create(1)).withEntry(ItemEntry.builder(FLESH_LEATHER));
                supplier.withPool(poolBuilder);

            }
        });
    }

    public FleshLeather() {
        super();
        this.setItemName(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName()));
        this.registerItem();
    }
}