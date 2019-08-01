package io.github.xeonpowder.fabric.rpg.client.mixin.entity;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

        @Inject(at = @At(value = "RETURN"), method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V")
        public void onDamageMixin(DamageSource damageSource, float float_1, CallbackInfo ci) {
                if (damageSource.getAttacker() != null && damageSource.getAttacker() instanceof PlayerEntity) {
                        ItemStack lastPlayerAttackingWithWhichItemStack = ((PlayerEntity) damageSource.getAttacker())
                                        .getMainHandStack();
                        PlayerEntity lastPlayerAttacking = ((PlayerEntity) damageSource.getAttacker());

                        if (lastPlayerAttacking != null && lastPlayerAttackingWithWhichItemStack != null) {
                                if (!lastPlayerAttackingWithWhichItemStack.hasTag()) {
                                        lastPlayerAttackingWithWhichItemStack.setTag(new CompoundTag());
                                }
                                CompoundTag playerItemStackCompoundTag = lastPlayerAttackingWithWhichItemStack.getTag();
                                playerItemStackCompoundTag = FabricRPGItemStackStats
                                                .calculateNewValueForOnPlayerAttack(playerItemStackCompoundTag);
                                lastPlayerAttackingWithWhichItemStack.setTag(playerItemStackCompoundTag);

                        }
                }

        }

        @Inject(at = @At(value = "RETURN"), method = "onDeath")
        public void onLivingEntityDeathReturn(DamageSource damageSource, CallbackInfo ci) {
                if (damageSource.getAttacker() != null && damageSource.getAttacker() instanceof PlayerEntity) {
                        System.out.println("attacker is instance of player entity");
                        ItemStack lastPlayerAttackingWithWhichItemStack = ((PlayerEntity) damageSource.getAttacker())
                                        .getMainHandStack();
                        PlayerEntity lastPlayerAttacking = ((PlayerEntity) damageSource.getAttacker());

                        if (lastPlayerAttacking != null && lastPlayerAttackingWithWhichItemStack != null) {
                                if (!lastPlayerAttackingWithWhichItemStack.hasTag()) {
                                        lastPlayerAttackingWithWhichItemStack.setTag(new CompoundTag());
                                }
                                CompoundTag playerItemStackCompoundTag = lastPlayerAttackingWithWhichItemStack.getTag();
                                playerItemStackCompoundTag = FabricRPGItemStackStats
                                                .calculateNewValueForOnLivingEntityDeath(playerItemStackCompoundTag);
                                lastPlayerAttackingWithWhichItemStack.setTag(playerItemStackCompoundTag);

                        }

                }
        }

}