package io.github.xeonpowder.fabric.rpg.client.mixin.entity.player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.stat.FabricRPGStatTypes;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(at = @At(value = "RETURN"), method = "attackLivingEntity")
    public void attackLivingEntityReturnMixin(LivingEntity livingEntity, CallbackInfo ci) {
        ItemStack stack = ((PlayerEntity) (Object) this).getMainHandStack();
        CompoundTag stackTag;
        if (stack.hasTag()) {
            stackTag = stack.getTag();
        } else {
            stackTag = new CompoundTag();
            stack.setTag(stackTag);
        }
        FabricRPGStatTypes.fabricRPGStatTypesToClassMap.keySet().forEach(string -> {
            if (stackTag.containsKey("fabric_rpg.stat." + string)) {
                stackTag.putFloat("fabric_rpg.stat." + string,
                        ((float) (stackTag.getFloat("fabric_rpg.stat." + string) + .1)));
            } else {
                stackTag.putFloat("fabric_rpg.stat" + string, 0);
            }
        });
    }

}