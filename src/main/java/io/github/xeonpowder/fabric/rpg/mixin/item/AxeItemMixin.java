package io.github.xeonpowder.fabric.rpg.mixin.item;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * AxeItemMixin
 */
@Mixin(Item.class)
public class AxeItemMixin {
    @Inject(at = @At(value = "RETURN"), method = "use")
    public void rightClickWithAxe(World world, PlayerEntity playerEntity, Hand hand,
            CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (world.isClient) {
            if (playerEntity.getMainHandStack().getItem() instanceof AxeItem) {
                FabricRPG.PlayerTimberComponent.get(playerEntity).getTimber().toggle();
            }
        }
    }
}