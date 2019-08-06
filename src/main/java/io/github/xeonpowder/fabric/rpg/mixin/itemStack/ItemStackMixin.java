package io.github.xeonpowder.fabric.rpg.mixin.itemStack;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltipCallback;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGStatTypes;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(at = @At(value = "RETURN"), method = "<init>(Lnet/minecraft/item/ItemConvertible;I)V")
    public void setupItemStackMixinForFabricRPG(ItemConvertible itemConvertible_1, int int_1, CallbackInfo ci) {
        ItemStack newItemStack = ((ItemStack) (Object) this);
        CompoundTag newItemStackCompoundTag = newItemStack.hasTag() ? newItemStack.getTag() : new CompoundTag();
        FabricRPGStatTypes.fabricRPGStatTypesToClassMap.forEach((string, statClass) -> {
            newItemStackCompoundTag.putFloat("fabric_rpg.stat." + string, 0);
        });
        newItemStack.setTag(newItemStackCompoundTag);
    }

    @Inject(at = @At(value = "RETURN"), method = "<init>(Lnet/minecraft/nbt/CompoundTag;)V")
    public void setupItemStackMixinForFabricRPG(CompoundTag compoundTag, CallbackInfo ci) {
        ItemStack newItemStack = ((ItemStack) (Object) this);
        CompoundTag newItemStackCompoundTag = newItemStack.hasTag() ? newItemStack.getTag() : new CompoundTag();
        FabricRPGStatTypes.fabricRPGStatTypesToClassMap.forEach((string, statClass) -> {
            if (!newItemStackCompoundTag.containsKey(string)) {
                newItemStackCompoundTag.putFloat("fabric_rpg.stat." + string, 0);
            }

        });
        newItemStack.setTag(newItemStackCompoundTag);
    }

    @Inject(at = @At(value = "RETURN"), method = "getTooltip")
    public void getTooltip(PlayerEntity playerEntity, TooltipContext tooltipContext,
            CallbackInfoReturnable<List<Text>> info) {
        if (playerEntity != null) {
            FabricRPGItemTooltipCallback.EVENT.invoker().getTooltip(((ItemStack) (Object) this), playerEntity,
                    tooltipContext, info.getReturnValue());
        }

    }
}