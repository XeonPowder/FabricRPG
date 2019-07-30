package io.github.xeonpowder.fabric.rpg.entity;

import java.util.HashMap;

import net.minecraft.entity.LivingEntity;

public class FabricRPGLivingEntityDB {
    private HashMap<FabricRPGLivingEntity, LivingEntity> fabricRPGLivingEntityMap;
    public String livingEntityName;

    public FabricRPGLivingEntityDB(String livingEntityName) {
        this.livingEntityName = livingEntityName;
    }

    public void putLivingEntity(FabricRPGLivingEntity fabricRPGLivingEntity, LivingEntity livingEntity) {
        fabricRPGLivingEntityMap.put(fabricRPGLivingEntity, livingEntity);
    }

    public FabricRPGLivingEntity getFabricRPGLivingEntity(LivingEntity livingEntity) {
        FabricRPGLivingEntity fromHashMap = null;
        for (FabricRPGLivingEntity fabricRPGLivingEntity : fabricRPGLivingEntityMap.keySet()) {
            if (fabricRPGLivingEntityMap.get(fabricRPGLivingEntity).equals(livingEntity)) {
                fromHashMap = fabricRPGLivingEntity;
            }
        }
        if (fromHashMap != null) {
            return fromHashMap;
        } else {
            FabricRPGLivingEntity newfabricRPGLivingEntity = new FabricRPGLivingEntity(this.livingEntityName);
            putLivingEntity(newfabricRPGLivingEntity, livingEntity);
            return newfabricRPGLivingEntity;
        }
    }

    public LivingEntity getLivingEntity(FabricRPGLivingEntity fabricRPGLivingEntity) {
        return fabricRPGLivingEntityMap.get(fabricRPGLivingEntity);
    }

    public void attachFabricRPG(LivingEntity livingEntity) {
        getFabricRPGLivingEntity(livingEntity);
    }
}