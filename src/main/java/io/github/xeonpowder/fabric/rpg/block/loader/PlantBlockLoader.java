package io.github.xeonpowder.fabric.rpg.block.loader;

import java.util.Set;

import org.reflections.Reflections;

import io.github.xeonpowder.fabric.rpg.block.FabricRPGPlantBlock;

/**
 * PlantBlockLoader
 */
public class PlantBlockLoader {

    public PlantBlockLoader(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends FabricRPGPlantBlock>> fabricRPGItems = reflections.getSubTypesOf(FabricRPGPlantBlock.class);
        for (Class<? extends FabricRPGPlantBlock> fabricRPGItem : fabricRPGItems) {
            try {
                fabricRPGItem.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}