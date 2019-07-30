package io.github.xeonpowder.fabric.rpg.stat;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FabricRPGItemStackStats {
    private HashMap<String, FabricRPGItemStackStatInterface> statsMap;

    public FabricRPGItemStackStats(Map<String, FabricRPGItemStackStatInterface> statsMap) {
        if (statsMap != null) {
            this.statsMap = new HashMap<>(statsMap);
        } else {
            this.statsMap = new HashMap<String, FabricRPGItemStackStatInterface>();
            this.setupStatsMap();
        }
    }

    public void setupStatsMap() {
        FabricRPGStatTypes.fabricRPGStatTypesToClassMap.forEach((string, extendedStatClass) -> {
            try {
                FabricRPGItemStackStatInterface newStatClassInstance = extendedStatClass.getConstructor().newInstance();
                this.statsMap.put(string, newStatClassInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public HashMap<String, FabricRPGItemStackStatInterface> getStatsMap() {
        return this.statsMap;
    }

    public void addToStat(String key, float value) {
        this.setStat(key, statsMap.get(key).getStatValue() + value);
    }

    public void subtractFromStat(String key, float value) {
        this.setStat(key, statsMap.get(key).getStatValue() - value);
    }

    public void setStat(String key, float value) {
        statsMap.get(key).setStatValue(value);
    }

    public static HashMap<String, FabricRPGItemStackStatInterface> getStatsMap(FabricRPGItemStackStats stackStats) {
        return stackStats.getStatsMap();
    }

}