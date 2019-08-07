package io.github.xeonpowder.fabric.rpg.client;

import java.util.ArrayList;

import io.github.xeonpowder.fabric.rpg.block.FabricRPGBlockItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltip;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltipCallback;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStats;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;

/**
 * FabricRPG
 */
public class FabricRPGClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricRPGItemTooltipCallback.EVENT.register((stack, player, tooltipContext, components) -> {
            if (stack != null && tooltipContext != null && components != null) {
                if (player.world.isAreaLoaded(player.getBlockPos(), player.getBlockPos())) {
                    boolean isFabricRPGItem = (stack.getItem() instanceof FabricRPGItem);
                    boolean isFabricRPGBlockItem = (stack.getItem() instanceof FabricRPGBlockItem);
                    if (stack.getMaxDamage() > 0) {
                        components.addAll(
                                FabricRPGItemTooltip.addDurabilityOfItemStackToTooltip(new ArrayList<Text>(), stack));
                    }
                    CompoundTag stackTag = stack.hasTag() ? stack.getTag() : new CompoundTag();
                    if (isFabricRPGItem || isFabricRPGBlockItem) {
                        components.addAll(FabricRPGItemTooltip.createTooltipWithStatsForFabricRPGItem(
                                isFabricRPGItem ? "item" : "block",
                                (isFabricRPGItem ? ((FabricRPGItem) stack.getItem())
                                        : ((FabricRPGBlockItem<?>) stack.getItem())).getTranslationKey(),
                                new ArrayList<Text>(), FabricRPGItemTooltip.WRAP_WIDTH,
                                FabricRPGItemStackStats.createStatsMapFromCompoundTag(stackTag)));

                    } else {
                        components.addAll(FabricRPGItemTooltip.createStatsTooltipForNonFabricRPGItem(
                                new ArrayList<Text>(), FabricRPGItemTooltip.WRAP_WIDTH,
                                FabricRPGItemStackStats.createStatsMapFromCompoundTag(stackTag)));
                    }
                }

            }

        });
    }

}