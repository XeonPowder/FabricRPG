package io.github.xeonpowder.fabric.rpg.client.mixin.fabric.rpg;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.client.entity.passive.events.SheepEvents;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltipCallback;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStack;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStackDB;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

@Mixin(FabricRPG.class)
public class MixinCallbackRegistration {
    @Inject(at = @At("RETURN"), method = "onInitialize()V")
    private void onInitialize(CallbackInfo ci) {

        FabricRPGItemTooltipCallback.EVENT.register((stack, player, tooltipContext, components) -> {
            if (stack != null && tooltipContext != null && components != null) {
                if (player.world.isAreaLoaded(player.getBlockPos(), player.getBlockPos())) {
                    boolean isFabricRPGItem = (stack.getItem() instanceof FabricRPGItem);
                    if (isFabricRPGItem) {
                        FabricRPGItemStackDB fabricRPGItems = FabricRPG.ItemStackDB
                                .get(((FabricRPGItem) stack.getItem()));
                        if (fabricRPGItems != null) {
                            if (fabricRPGItems.getFabricRPGItemStack(stack) != null) {
                                FabricRPGItemStack fabricRPGItemStack = fabricRPGItems.getFabricRPGItemStack(stack);
                                List<Text> fabricRPGItemStackTranslatedStatsAndTooltipText = fabricRPGItemStack
                                        .getTranslatedStatsText();
                                if (fabricRPGItemStackTranslatedStatsAndTooltipText != null) {
                                    components.addAll(fabricRPGItemStackTranslatedStatsAndTooltipText);
                                } else {
                                    System.err.println(FabricRPG.asClientOrServer(
                                            "fabricRPGItemStackTranslatedStatsAndTooltipText is null -- MixinCallbackRegistration.java --"));
                                }

                            } else {
                                System.err.println(FabricRPG.asClientOrServer(
                                        "fabricRPGItemStack is null -- MixinCallbackRegistration.java --"));
                                fabricRPGItems.attachFabricRPG(stack);
                            }

                        } else {
                            System.err.println(FabricRPG
                                    .asClientOrServer("fabricRPGItems is null -- MixinCallbackRegistration.java --"));
                            FabricRPG.ItemStackDB.put(((FabricRPGItem) stack.getItem()),
                                    new FabricRPGItemStackDB(((FabricRPGItem) stack.getItem())));
                        }

                    } else {
                        FabricRPGItemStackDB fabricRPGItems = FabricRPG.ItemStackDB.get(stack.getItem());
                        if (fabricRPGItems != null) {
                            if (fabricRPGItems.getFabricRPGItemStack(stack) != null) {
                                FabricRPGItemStack fabricRPGItemStack = fabricRPGItems.getFabricRPGItemStack(stack);
                                List<Text> fabricRPGItemStackTranslatedStatsText = fabricRPGItemStack
                                        .getTranslatedStatsText();
                                if (fabricRPGItemStackTranslatedStatsText != null) {
                                    components.addAll(fabricRPGItemStackTranslatedStatsText);
                                } else {
                                    System.err.println(FabricRPG.asClientOrServer(
                                            "fabricRPGItemStackTranslatedStatsText is null -- MixinCallbackRegistration.java"));
                                }

                            } else {
                                System.err.println(FabricRPG.asClientOrServer(
                                        "fabricRPGItemStack is null -- MixinCallbackRegistration.java"));
                                fabricRPGItems.attachFabricRPG(stack);
                            }

                        } else {
                            System.err.println(FabricRPG
                                    .asClientOrServer("fabricRPGItems is null -- MixinCallbackRegistration.java"));
                            FabricRPG.ItemStackDB.put((stack.getItem()), new FabricRPGItemStackDB((stack.getItem())));
                        }
                    }
                }

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
