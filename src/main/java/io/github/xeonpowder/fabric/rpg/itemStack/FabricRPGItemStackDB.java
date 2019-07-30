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
        fabricRPGItemStack.setVanillaStack(itemStack);
    }

    public FabricRPGItemStack getFabricRPGItemStack(ItemStack itemStack) {
        FabricRPGItemStack fromHashMap = null;
        for (FabricRPGItemStack fabricRPGItemStack : fabricRPGItemStackMap.keySet()) {
            if (fabricRPGItemStackMap.get(fabricRPGItemStack).equals(itemStack)) {
                fromHashMap = fabricRPGItemStack;
            }
        }
        return fromHashMap;
    }

    public ItemStack getItemStack(FabricRPGItemStack fabricRPGItemStack) {
        ItemStack fromHashMap = fabricRPGItemStackMap.get(fabricRPGItemStack);
        return fromHashMap;
    }

    public void attachFabricRPG(ItemStack itemStack) {
        FabricRPGItemStack fromHashMap = getFabricRPGItemStack(itemStack);
        if (fromHashMap == null) {
            putItemStack(new FabricRPGItemStack(itemStack.getItem()), itemStack);
        }
    }

    public void dropFabricItemStack(FabricRPGItemStack fabricRPGItemStack) {
        System.out.println("drop item stack: " + fabricRPGItemStack);
        fabricRPGItemStackMap.remove(fabricRPGItemStack);
    }
}