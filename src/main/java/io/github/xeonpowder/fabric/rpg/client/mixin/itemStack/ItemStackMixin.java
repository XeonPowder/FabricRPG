package io.github.xeonpowder.fabric.rpg.client.mixin.itemStack;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import io.github.xeonpowder.fabric.rpg.FabricRPG;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(at = @At(value = "RETURN"), method = "<init>*")
    public void setupItemStackMixin(CallbackInfo ci) {
        FabricRPG.ItemStackDB.get((Item) ((ItemStack) (Object) this).getItem())
                .attachFabricRPG(((ItemStack) (Object) this));
    }

}