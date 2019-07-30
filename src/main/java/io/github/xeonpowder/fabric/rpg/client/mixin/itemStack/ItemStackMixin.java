package io.github.xeonpowder.fabric.rpg.client.mixin.itemStack;

import java.util.List;
import java.util.regex.Pattern;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltip;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltipCallback;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(at = @At(value = "RETURN"), method = "<init>*")
    public void setupItemStackMixin(CallbackInfo ci) {

        // if (FabricRPG.asClientOrServer("").equals(FabricRPG.CLIENT_TAG)) {
        // if (((Item) ((ItemStack) (Object) this).getItem()) instanceof FabricRPGItem)
        // {
        // if (FabricRPG.ItemStackDB.get(((FabricRPGItem) ((ItemStack) (Object)
        // this).getItem())) != null) {

        // if (FabricRPG.ItemStackDB.get(((FabricRPGItem) ((ItemStack) (Object)
        // this).getItem()))
        // .getFabricRPGItemStack(((ItemStack) (Object) this)) == null) {
        // FabricRPG.ItemStackDB.get(((FabricRPGItem) ((ItemStack) (Object)
        // this).getItem()))
        // .attachFabricRPG(((ItemStack) (Object) this));
        // System.out.println(FabricRPG.asClientOrServer("registered ItemStack for item
        // "
        // + (new TranslatableText(
        // ((FabricRPGItem) ((ItemStack) (Object) this).getItem()).getTranslationKey()))
        // .asString()
        // + " in FabricRPG.ItemStackDB: " + ((ItemStack) (Object) this).hashCode()));
        // }
        // }

        // } else {
        // if (FabricRPG.ItemStackDB.get(((Item) ((ItemStack) (Object) this).getItem()))
        // != null) {
        // if (FabricRPG.ItemStackDB.get(((Item) ((ItemStack) (Object) this).getItem()))
        // .getFabricRPGItemStack(((ItemStack) (Object) this)) == null) {
        // FabricRPG.ItemStackDB.get(((Item) ((ItemStack) (Object) this).getItem()))
        // .attachFabricRPG(((ItemStack) (Object) this));
        // System.out.println(FabricRPG.asClientOrServer("registered ItemStack for item
        // "
        // + (new TranslatableText(
        // ((Item) ((ItemStack) (Object)
        // this).getItem()).getTranslationKey())).asString()
        // + " in FabricRPG.ItemStackDB: " + ((ItemStack) (Object) this).hashCode()));
        // }
        // }

        // }
        // }

    }

    @Inject(at = @At(value = "RETURN"), method = "getTooltip")
    public void getTooltip(PlayerEntity playerEntity, TooltipContext tooltipContext,
            CallbackInfoReturnable<List<Text>> info) {
        if (playerEntity != null) {
            FabricRPGItemTooltipCallback.EVENT.invoker().getTooltip((ItemStack) (Object) this, playerEntity,
                    tooltipContext, info.getReturnValue());
        }

    }
}