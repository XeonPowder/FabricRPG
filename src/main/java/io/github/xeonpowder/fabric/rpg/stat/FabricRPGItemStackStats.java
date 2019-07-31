package io.github.xeonpowder.fabric.rpg.stat;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.CompoundTag;

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
                FabricRPGItemStackStatInterface newStatClassInstance = extendedStatClass.newInstance();
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

    public static HashMap<String, FabricRPGItemStackStatInterface> createStatsMapFromCompoundTag(CompoundTag tag) {
        HashMap<String, FabricRPGItemStackStatInterface> statsMap = new HashMap<>();
        tag.getKeys().forEach(string -> {
            if (string.contains("fabric_rpg.stat")) {
                String statName = string.substring(string.lastIndexOf(".") + 1);
                FabricRPGItemStackStatInterface newStatClassInstance = null;
                try {
                    newStatClassInstance = FabricRPGStatTypes.fabricRPGStatTypesToClassMap.get(statName)
                            .getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (newStatClassInstance != null) {
                    newStatClassInstance.setStatValue(tag.getFloat(string));
                    statsMap.put(statName, newStatClassInstance);
                }

            }
        });
        return statsMap;
    }

    public static CompoundTag calculateNewValues(CompoundTag playerItemStackCompoundTag) {
        playerItemStackCompoundTag.getKeys().forEach(key -> {
            if (key.contains("fabric_rpg.stat")) {
                Float oldValue = playerItemStackCompoundTag.getFloat(key);
                Float newValue = oldValue;
                FabricRPGItemStackStatInterface statInstance = null;
                try {
                    statInstance = FabricRPGStatTypes.fabricRPGStatTypesToClassMap.get(key).getConstructor()
                            .newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (statInstance != null) {
                    newValue = ((float) (statInstance.calculateNewValue(oldValue)));
                    playerItemStackCompoundTag.putFloat(key, newValue);
                }
            }

        });
        return playerItemStackCompoundTag;
    }

}