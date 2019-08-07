package io.github.xeonpowder.fabric.rpg.mixin.item;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.Item;

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