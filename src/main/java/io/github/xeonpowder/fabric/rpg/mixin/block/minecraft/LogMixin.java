package io.github.xeonpowder.fabric.rpg.mixin.block.minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.timber.LogBreaker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(Block.class)
public class LogMixin {

    @Inject(at = @At(value = "INVOKE"), method = "onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)V")
    public void onLogBreak(World world, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity,
            CallbackInfo ci) {
        if (!world.isClient) {
            if (playerEntity.getStackInHand(playerEntity.getActiveHand()).getItem() instanceof AxeItem) {
                if (FabricRPG.PlayerTimberComponent.get(playerEntity).getTimber().isActive()) {
                    System.out.println(blockState.getBlock());
                    if (blockState.getBlock() instanceof LogBlock) {
                        LogBreaker.onBreak(world, blockPos);
                    }
                }
            }
        }
    }
}
