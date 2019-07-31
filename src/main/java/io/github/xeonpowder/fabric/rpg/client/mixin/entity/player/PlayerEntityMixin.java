package io.github.xeonpowder.fabric.rpg.client.mixin.entity.player;

import java.lang.reflect.InvocationTargetException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStatInterface;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGStatTypes;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    // @Inject(at = @At(value = "RETURN"), method = "attack")
    // public void attackReturnMixin(Entity entity, CallbackInfo ci) {
    // if (entity instanceof LivingEntity) {
    // ItemStack stack = ((PlayerEntity) (Object) this).getMainHandStack();
    // CompoundTag stackTag;
    // if (stack.hasTag()) {
    // stackTag = stack.getTag();
    // } else {
    // stackTag = new CompoundTag();
    // stack.setTag(stackTag);
    // }
    // FabricRPGStatTypes.fabricRPGStatTypesToClassMap.keySet().forEach(string -> {
    // FabricRPGItemStackStatInterface statInstance = null;
    // try {
    // statInstance =
    // FabricRPGStatTypes.fabricRPGStatTypesToClassMap.get(string).getDeclaredConstructor()
    // .newInstance();
    // } catch (InstantiationException | IllegalAccessException |
    // IllegalArgumentException
    // | InvocationTargetException | NoSuchMethodException | SecurityException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // if (statInstance != null) {
    // if (stackTag.containsKey("fabric_rpg.stat." + string)) {
    // float oldValue = stackTag.getFloat("fabric_rpg.stat." + string);
    // float newValue = statInstance.calculateNewValueForOnPlayerAttack(oldValue);
    // System.out.println(oldValue);
    // System.out.println(newValue);
    // stackTag.putFloat("fabric_rpg.stat." + string, newValue);
    // } else {
    // stackTag.putFloat("fabric_rpg.stat" + string, 0);
    // }
    // }

    // });
    // stack.setTag(stackTag);
    // }

    // }

}