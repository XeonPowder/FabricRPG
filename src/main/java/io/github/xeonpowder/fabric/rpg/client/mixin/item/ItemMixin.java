package io.github.xeonpowder.fabric.rpg.client.mixin.item;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.TUSK__3.panI18n.FormattingEngine;

import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

@Mixin(Item.class)
public class ItemMixin {
    // @Inject(at = @At(value = "RETURN"), method = "getName()V;", cancellable =
    // true)
    // public Text formattingEngineItemGetNameMixin(CallbackInfoReturnable<Text>
    // cir) {
    // System.out.println(cir.getReturnValue().asString());
    // Text returnValue = new LiteralText(
    // FormattingEngine.replaceColorCodeEnumInString(cir.getReturnValue().asString()));
    // cir.setReturnValue(returnValue);
    // return returnValue;
    // }

    // @Inject(at = @At(value = "RETURN"), method =
    // "Lnet/minecraft/item/Item;getName(Lnet/minecraft/item/ItemStack;)Lnet/minecrft/text/Text;",
    // cancellable = true)
    // public Text formattingEngineItemGetNameFromItemStackMixin(ItemStack
    // itemstack_1, CallbackInfoReturnable<Text> cir) {
    // System.out.println(cir.getReturnValue().asString());
    // Text returnValue = new LiteralText(
    // FormattingEngine.replaceColorCodeEnumInString(cir.getReturnValue().asString()));
    // cir.setReturnValue(returnValue);
    // return returnValue;
    // }
}