package io.github.xeonpowder.fabric.rpg.stat.loader;

import java.util.Set;

import org.reflections.Reflections;

import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStatInterface;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGStatTypes;

public class StatLoader {
    public StatLoader(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends FabricRPGItemStackStatInterface>> fabricRPGItemStackStats = reflections
                .getSubTypesOf(FabricRPGItemStackStatInterface.class);
        for (Class<? extends FabricRPGItemStackStatInterface> fabricRPGItemStackStat : fabricRPGItemStackStats) {

            try {
                FabricRPGItemStackStatInterface newInstance = fabricRPGItemStackStat.getConstructor().newInstance();
                FabricRPGStatTypes.fabricRPGStatTypesToClassMap.put(newInstance.getStatName(), newInstance.getClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}