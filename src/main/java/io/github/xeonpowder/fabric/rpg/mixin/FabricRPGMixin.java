package io.github.xeonpowder.fabric.rpg.mixin;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.block.FabricRPGBlockItem;
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
public class FabricRPGMixin {
    @Inject(at = @At("RETURN"), method = "onInitialize()V")
    private void onInitialize(CallbackInfo ci) {


    }
}
