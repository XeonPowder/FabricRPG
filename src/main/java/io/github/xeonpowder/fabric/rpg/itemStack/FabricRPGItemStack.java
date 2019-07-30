package io.github.xeonpowder.fabric.rpg.itemStack;

import java.util.ArrayList;
import java.util.List;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltip;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStats;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class FabricRPGItemStack {
    FabricRPGItemStackStats stats;
    boolean isNotFabricRPG = false;
    ItemStack vanillaStack = ItemStack.EMPTY;
    FabricRPGItem rpgItem = null;
    Item customItem = null;

    public FabricRPGItemStack(FabricRPGItem item) {
        this.rpgItem = item;
        this.vanillaStack = FabricRPG.ItemStackDB.get(rpgItem).getItemStack(this);
        this.stats = new FabricRPGItemStackStats(null);
    }

    public FabricRPGItemStack(Item customItem) {
        this.stats = new FabricRPGItemStackStats(null);

        if (customItem instanceof FabricRPGItem) {
            this.rpgItem = ((FabricRPGItem) customItem);
            this.vanillaStack = FabricRPG.ItemStackDB.get(this.rpgItem).getItemStack(this);
        } else {
            this.isNotFabricRPG = true;
            this.customItem = customItem;
            this.vanillaStack = FabricRPG.ItemStackDB.get(customItem).getItemStack(this);
        }
    }

    public FabricRPGItemStack(ItemStack itemStack) {
        FabricRPG.ItemStackDB.get(itemStack.getItem()).getFabricRPGItemStack(itemStack);
    }

    public FabricRPGItemStackStats getStats() {
        return this.stats;
    }

    public Item getItem() {
        if (this.rpgItem != null) {
            return this.rpgItem;
        }
        return this.customItem;
    }

    public List<Text> getTranslatedStatsText() {
        if (this.customItem != null) {
            return FabricRPGItemTooltip.createTooltipWithStatsForNonFabricRPGItem(new ArrayList<Text>(), 70,
                    this.stats.getStatsMap(), this.customItem);
        } else {
            return FabricRPGItemTooltip.createTooltipWithStatsForFabricRPGItem(rpgItem.getItemName(),
                    new ArrayList<Text>(), 70, this.stats.getStatsMap());
        }
    }

}