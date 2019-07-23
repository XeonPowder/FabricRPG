package io.github.xeonpowder.fabric.rpg.client.mixin.fabric.rpg;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.client.entity.passive.events.SheepEvents;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

@Mixin(FabricRPG.class)
public class SheepMixin {
    @Inject(at = @At("HEAD"), method = "onInitialize()V")
    private void onInitialize(CallbackInfo ci) {
        SheepEvents.SheepShearCallback.EVENT.register((player, sheep) -> {
            sheep.setSheared(true);

            // create diamond item entity at sheep position
            ItemStack stack = new ItemStack(Items.DIAMOND);
            ItemEntity itemEntity = new ItemEntity(player.world, sheep.x, sheep.y, sheep.z, stack);
            player.world.spawnEntity(itemEntity);

            return ActionResult.FAIL;
        });
        SheepEvents.DeathByPlayerCallback.EVENT.register((player, sheep) -> {
            sheep.setSheared(true);

            // create diamond item entity at sheep position
            ItemStack stack = new ItemStack(Items.DIAMOND);
            ItemEntity itemEntity = new ItemEntity(player.world, sheep.x, sheep.y, sheep.z, stack);
            player.world.spawnEntity(itemEntity);

            return ActionResult.FAIL;
        });
        SheepEvents.DeathByEnvironmentCallback.EVENT.register((player, sheep) -> {
            sheep.setSheared(true);

            // create diamond item entity at sheep position
            ItemStack stack = new ItemStack(Items.DIAMOND);
            ItemEntity itemEntity = new ItemEntity(player.world, sheep.x, sheep.y, sheep.z, stack);
            player.world.spawnEntity(itemEntity);

            return ActionResult.FAIL;
        });
    }
}
