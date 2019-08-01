package io.github.xeonpowder.fabric.rpg.client.mixin.fabric.rpg;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.client.entity.passive.events.SheepEvents;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltip;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltipCallback;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStats;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
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
                    // components.forEach(text -> {
                    // text = new
                    // LiteralText(FormattingEngine.replaceColorCodeEnumInString(text.asString()));
                    // });
                    CompoundTag stackTag = stack.hasTag() ? stack.getTag() : new CompoundTag();
                    if (isFabricRPGItem) {
                        components.addAll(FabricRPGItemTooltip.createTooltipWithStatsForFabricRPGItem(
                                ((FabricRPGItem) stack.getItem()).getItemName(), new ArrayList<Text>(),
                                FabricRPGItemTooltip.WRAP_WIDTH,
                                FabricRPGItemStackStats.createStatsMapFromCompoundTag(stackTag)));

                    } else {
                        components.addAll(FabricRPGItemTooltip.createStatsTooltipForNonFabricRPGItem(
                                new ArrayList<Text>(), FabricRPGItemTooltip.WRAP_WIDTH,
                                FabricRPGItemStackStats.createStatsMapFromCompoundTag(stackTag)));
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
    }
}
