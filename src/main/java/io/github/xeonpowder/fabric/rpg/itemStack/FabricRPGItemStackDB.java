package io.github.xeonpowder.fabric.rpg.itemStack;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FabricRPGItemStackDB {
    private HashMap<FabricRPGItemStack, ItemStack> fabricRPGItemStackMap;
    public Item item;

    public FabricRPGItemStackDB(Item item) {
        this.item = item;
        fabricRPGItemStackMap = new HashMap<FabricRPGItemStack, ItemStack>();
    }

    public void putItemStack(FabricRPGItemStack fabricRPGItemStack, ItemStack itemStack) {
        fabricRPGItemStackMap.put(fabricRPGItemStack, itemStack);
    }

    public FabricRPGItemStack getFabricRPGItemStack(ItemStack itemStack) {
        FabricRPGItemStack fromHashMap = null;
        for (FabricRPGItemStack fabricRPGItemStack : fabricRPGItemStackMap.keySet()) {
            if (fabricRPGItemStackMap.get(fabricRPGItemStack).equals(itemStack)) {
                fromHashMap = fabricRPGItemStack;
            }
        }
        if (fromHashMap != null) {
            return fromHashMap;
        } else {
            FabricRPGItemStack newItemStack = new FabricRPGItemStack(this.item);
            putItemStack(newItemStack, itemStack);
            return newItemStack;
        }
    }

    public ItemStack getItemStack(FabricRPGItemStack fabricRPGItemStack) {
        ItemStack fromHashMap = fabricRPGItemStackMap.get(fabricRPGItemStack);
        if (fromHashMap != null) {
            return fromHashMap;
        } else {
            ItemStack newItemStack = new ItemStack(item);
            putItemStack(fabricRPGItemStack, newItemStack);
            return newItemStack;
        }
    }

    public void attachFabricRPG(ItemStack itemStack) {
        getFabricRPGItemStack(itemStack);
    }

    public void dropFabricItemStack(FabricRPGItemStack fabricRPGItemStack) {
        fabricRPGItemStackMap.remove(fabricRPGItemStack);
    }
}