package io.github.xeonpowder.fabric.rpg.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class FabricRPGLivingEntity {
    public PlayerEntity lastAttackingPlayer;
    public ItemStack lastAttackingPlayerItemStack;
    public String livingEntityName;

    public FabricRPGLivingEntity(String livingEntityName) {
        this.livingEntityName = livingEntityName;
    }
}