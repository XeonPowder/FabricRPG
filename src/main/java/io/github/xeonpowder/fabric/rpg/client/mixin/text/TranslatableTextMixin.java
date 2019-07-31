package io.github.xeonpowder.fabric.rpg.client.mixin.text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.TUSK__3.panI18n.FormattingEngine;
import net.minecraft.text.TranslatableText;

@Mixin(TranslatableText.class)
public class TranslatableTextMixin {
    @Inject(at = @At(value = "TAIL"), method = "Lnet/minecraft/text/TranslatableText;asString()Ljava/lang/String;", cancellable = true)
    public void parseFabricRPGCustomFormatting(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(FormattingEngine.replaceColorCodeEnumInString(cir.getReturnValue()));
    }
}