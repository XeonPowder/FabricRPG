package io.github.xeonpowder.fabric.rpg.item.loader;

import java.util.ArrayList;
import java.util.Set;

import org.reflections.Reflections;

import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;

public class ItemLoader {
    private ArrayList<FabricRPGItem> items = new ArrayList<>();

    public ItemLoader(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends FabricRPGItem>> fabricRPGItems = reflections.getSubTypesOf(FabricRPGItem.class);
        for (Class<? extends FabricRPGItem> fabricRPGItem : fabricRPGItems) {
            try {
                items.add(fabricRPGItem.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<FabricRPGItem> getItems() {
        return this.items;
    }
}