package io.github.xeonpowder.fabric.rpg.mixin.entity;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.xeonpowder.fabric.rpg.block.FabricRPGHeadBlock;

/**
 * MobEntityMixin
 */
@Mixin(MobEntity.class)
public class MobEntityMixin {

    @Inject(at = @At("TAIL"), method = "getPreferredEquipmentSlot", cancellable = true)
    private static void getPreferredEquipmentSlot(ItemStack itemStack, CallbackInfoReturnable<EquipmentSlot> cir) {
        EquipmentSlot equipmentSlot = cir.getReturnValue();

        if (equipmentSlot == EquipmentSlot.MAINHAND
                && ((BlockItem) itemStack.getItem()).getBlock() instanceof FabricRPGHeadBlock) {
            cir.setReturnValue(EquipmentSlot.HEAD);
        }
    }
}