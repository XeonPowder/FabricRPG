package io.github.xeonpowder.fabric.rpg.client.mixin.itemStack;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import blue.endless.jankson.annotation.Nullable;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltipCallback;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

/**
 * ItemStackMixin
 */
@Mixin(ItemStack.class)
public class ClientItemStackMixin {

    @Inject(at = @At(value = "RETURN"), method = "getTooltip")
    public void getTooltip(@Nullable PlayerEntity playerEntity, TooltipContext tooltipContext,
            CallbackInfoReturnable<List<Text>> info) {
        if (playerEntity != null) {
            FabricRPGItemTooltipCallback.EVENT.invoker().getTooltip(((ItemStack) (Object) this), playerEntity,
                    tooltipContext, info.getReturnValue());
        }

    }
}