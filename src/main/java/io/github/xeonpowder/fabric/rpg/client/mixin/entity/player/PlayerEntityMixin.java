package io.github.xeonpowder.fabric.rpg.client.mixin.entity.player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.entity.FabricRPGLivingEntity;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(at = @At(value = "RETURN"), method = "attackLivingEntity")
    public void savePlayerDataToLivingEntityMixin(LivingEntity livingEntity, CallbackInfo ci) {
        if (livingEntity != null) {
            FabricRPGLivingEntity fabricRPGLivingEntity = FabricRPG.LivingEntityDB.get(livingEntity.getUuidAsString())
                    .getFabricRPGLivingEntity(livingEntity);
            fabricRPGLivingEntity.lastAttackingPlayer = (PlayerEntity) (Object) this;
            fabricRPGLivingEntity.lastAttackingPlayerItemStack = fabricRPGLivingEntity.lastAttackingPlayer
                    .getMainHandStack();
            FabricRPG.LivingEntityDB.get(livingEntity.getUuidAsString()).putLivingEntity(fabricRPGLivingEntity,
                    livingEntity);
        }
    }

}