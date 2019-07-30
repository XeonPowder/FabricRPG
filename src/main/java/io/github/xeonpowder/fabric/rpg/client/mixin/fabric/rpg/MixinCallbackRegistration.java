package io.github.xeonpowder.fabric.rpg.client.mixin.fabric.rpg;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.client.entity.passive.events.SheepEvents;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltipCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

@Mixin(FabricRPG.class)
public class MixinCallbackRegistration {
    @Inject(at = @At("HEAD"), method = "onInitialize()V")
    private void onInitialize(CallbackInfo ci) {

        FabricRPGItemTooltipCallback.EVENT.register((stack, tooltipContext, components) -> {
            System.out.println("FabricRPGItemTooltipCallback stack hashCode: " + stack.hashCode());
            if (stack.getItem() instanceof FabricRPGItem) {
                components.addAll(FabricRPG.ItemStackDB.get((FabricRPGItem) stack.getItem())
                        .getFabricRPGItemStack(stack).getTranslatedStatsText());
            } else {
                components.addAll(FabricRPG.ItemStackDB.get(stack.getItem()).getFabricRPGItemStack(stack)
                        .getTranslatedStatsText());
            }

        });

        SheepEvents.SheepShearCallback.EVENT.register((player, sheep) -> {
            sheep.setSheared(true);

            // create diamond item entity at sheep position
            ItemStack stack = new ItemStack(Items.DIAMOND);
            ItemEntity itemEntity = new ItemEntity(player.world, sheep.x, sheep.y, sheep.z, stack);
            player.world.spawnEntity(itemEntity);

            return ActionResult.FAIL;
        });
        // SheepEvents.OnDeathCallback.EVENT.register((damageSource) -> {

        // });
        // SheepEvents.DeathByEnvironmentCallback.EVENT.register((player, sheep) -> {
        // sheep.setSheared(true);

        // // create diamond item entity at sheep position
        // ItemStack stack = new ItemStack(Items.DIAMOND);
        // ItemEntity itemEntity = new ItemEntity(player.world, sheep.x, sheep.y,
        // sheep.z, stack);
        // player.world.spawnEntity(itemEntity);

        // return ActionResult.FAIL;
        // });
    }
}
