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
                FabricRPGItemStackStatInterface newStatClassInstance = extendedStatClass.getDeclaredConstructor()
                        .newInstance();
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
            if (string != null) {
                if (string.contains("fabric_rpg.stat")) {
                    String statName = string.substring(string.lastIndexOf(".") + 1);
                    if (statName != null) {
                        FabricRPGItemStackStatInterface newStatClassInstance = null;
                        try {
                            newStatClassInstance = FabricRPGStatTypes.fabricRPGStatTypesToClassMap.get(statName)
                                    .getDeclaredConstructor().newInstance();
                        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                | InvocationTargetException | NoSuchMethodException | SecurityException
                                | NullPointerException e) {
                            e.printStackTrace();
                        }
                        if (newStatClassInstance != null) {
                            newStatClassInstance.setStatValue(tag.getFloat(string));
                            statsMap.put(statName, newStatClassInstance);
                        }
                    }

                }
            }

        });
        return statsMap;
    }

    public static CompoundTag calculateNewValueForOnPlayerAttack(CompoundTag playerItemStackCompoundTag) {
        CompoundTag newCompoundTag = new CompoundTag().copyFrom(playerItemStackCompoundTag);
        newCompoundTag.getKeys().forEach(string -> {
            System.out.println(string);
            if (string.contains("fabric_rpg.stat")) {
                String key = string.substring(string.lastIndexOf(".") + 1);
                Float oldValue = newCompoundTag.getFloat(string);
                Float newValue = Float.valueOf(oldValue);
                FabricRPGItemStackStatInterface statInstance = null;
                try {
                    statInstance = FabricRPGStatTypes.fabricRPGStatTypesToClassMap.get(key).getDeclaredConstructor()
                            .newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }
                if (statInstance != null) {
                    newValue = statInstance.calculateNewValueForOnPlayerAttack(oldValue);
                    newCompoundTag.putFloat(string, newValue);
                    System.out.printf("attack---old: %.2f new: %.2f\n", oldValue, newValue);

                }
            }

        });
        return newCompoundTag;
    }

    public static CompoundTag calculateNewValueForOnLivingEntityDeath(CompoundTag playerItemStackCompoundTag) {
        CompoundTag newCompoundTag = new CompoundTag().copyFrom(playerItemStackCompoundTag);
        newCompoundTag.getKeys().forEach(string -> {
            if (string.contains("fabric_rpg.stat")) {
                String key = string.substring(string.lastIndexOf(".") + 1);
                Float oldValue = newCompoundTag.getFloat(string);
                Float newValue = Float.valueOf(oldValue);
                FabricRPGItemStackStatInterface statInstance = null;
                try {
                    statInstance = FabricRPGStatTypes.fabricRPGStatTypesToClassMap.get(key).getDeclaredConstructor()
                            .newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }
                if (statInstance != null) {
                    newValue = statInstance.calculateNewValueForOnLivingEntityDeath(oldValue);
                    newCompoundTag.putFloat(string, newValue);
                    System.out.printf("death---old: %.2f new: %.2f\n", oldValue, newValue);
                }
            }
        });
        return newCompoundTag;
    }

}