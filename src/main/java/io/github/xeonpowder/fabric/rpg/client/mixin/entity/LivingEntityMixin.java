package io.github.xeonpowder.fabric.rpg.client.mixin.entity;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.entity.FabricRPGLivingEntityDB;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

        @Inject(at = @At(value = "RETURN"), method = "<init>*")
        public void onAnyConstructorReturn(CallbackInfo ci) {

        }

        @Inject(at = @At(value = "RETURN"), method = "onDeath")
        public void onLivingEntityDeathReturn(DamageSource damageSource, CallbackInfo ci) {
                if (damageSource.getAttacker() != null) {
                        System.out.println("living entity died, add blood to item");
                        if (damageSource.getAttacker() instanceof PlayerEntity) {
                                System.out.println("attacker is instance of player entity");
                                ItemStack lastPlayerAttackingWithWhichItemStack = ((PlayerEntity) damageSource
                                                .getAttacker()).getMainHandStack();
                                PlayerEntity lastPlayerAttacking = ((PlayerEntity) damageSource.getAttacker());
                                System.out.println(FabricRPG.asClientOrServer("last player attacking: "
                                                + lastPlayerAttacking.getDisplayName().asString()));

                                if (lastPlayerAttacking != null && lastPlayerAttackingWithWhichItemStack != null) {
                                        if (!lastPlayerAttackingWithWhichItemStack.hasTag()) {
                                                lastPlayerAttackingWithWhichItemStack.setTag(new CompoundTag());
                                        }
                                        CompoundTag playerItemStackCompoundTag = lastPlayerAttackingWithWhichItemStack
                                                        .getTag();

                                        lastPlayerAttackingWithWhichItemStack.setTag(FabricRPGItemStackStats
                                                        .calculateNewValues(playerItemStackCompoundTag));

                                }

                        }

                }
        }
}