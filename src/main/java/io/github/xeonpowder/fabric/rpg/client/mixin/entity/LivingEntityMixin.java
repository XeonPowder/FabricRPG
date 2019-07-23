package io.github.xeonpowder.fabric.rpg.client.mixin.entity;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public class LivingEntityMixin extends LivingEntity {
    protected LivingEntityMixin(EntityType<? extends LivingEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    public void setBlood(int blood) {

    }

    @Inject(at = @At(value = "RETURN", ordinal = 0), method = "onDeath")
    public void onLivingEntityDeath(DamageSource damageSource, CallbackInfo ci) {
        damageSource.getAttacker().getItemsHand().forEach(itemStack -> );
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return null;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot var1) {
        return null;
    }

    @Override
    public void setEquippedStack(EquipmentSlot var1, ItemStack var2) {

    }

    @Override
    public Arm getMainArm() {
        return null;
    }
}