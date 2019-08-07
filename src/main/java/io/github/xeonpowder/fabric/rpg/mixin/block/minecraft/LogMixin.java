package io.github.xeonpowder.fabric.rpg.mixin.block.minecraft;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

@Mixin(Block.class)
public class LogMixin {

    @Inject(at = @At(value = "INVOKE"), method = "onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)V")
    public void onLogBreak(World world_1, BlockPos blockPos_1, BlockState blockState_1, PlayerEntity playerEntity_1,
            CallbackInfo ci) {
        if (playerEntity_1.getMainHandStack().getItem() instanceof AxeItem) {
            if (FabricRPG.TIMBER.get(playerEntity_1).getTimber().isActive()) {
                Block brokenBlock = blockState_1.getBlock();
                LogBlock LOG = null;
                LeavesBlock LEAF = null;

                if (brokenBlock instanceof AirBlock) {
                    // System.out.println("broken block is an instance of airBlock:" + brokenBlock);
                    Block blockabove = world_1
                            .getBlockState(new BlockPos(blockPos_1.getX(), blockPos_1.getY() + 1, blockPos_1.getZ()))
                            .getBlock();
                    if (!(blockabove instanceof AirBlock)) {
                        LOG = ((LogBlock) blockabove);
                        // System.out.println(LOG.getTranslationKey());
                        Block leafBlockCorrispondingToLog = Registry.BLOCK.get(new Identifier((LOG.getTranslationKey()
                                .replace("block.minecraft.", "").replace("stripped_", "").replace("_log", "_leaves"))));
                        // System.out.println("leaf:" + leafBlockCorrispondingToLog);
                        if (leafBlockCorrispondingToLog instanceof LeavesBlock) {
                            LEAF = ((LeavesBlock) leafBlockCorrispondingToLog);
                            // System.out.println(LEAF);
                        }
                    }

                } else if (brokenBlock instanceof LogBlock) {
                    LOG = ((LogBlock) brokenBlock);
                    // System.out.println("broken block is an instance of logBlock:" +
                    // LOG.getTranslationKey());
                    Block leafBlockCorrispondingToLog = Registry.BLOCK.get(new Identifier((LOG.getTranslationKey()
                            .replace("block.minecraft.", "").replace("stripped_", "").replace("_log", "_leaves"))));
                    // System.out.println("leaf: " + leafBlockCorrispondingToLog);
                    if (leafBlockCorrispondingToLog instanceof LeavesBlock) {
                        LEAF = ((LeavesBlock) leafBlockCorrispondingToLog);
                        // System.out.println(LEAF);
                    }
                }

                if (LOG != null && LEAF != null) {
                    timber(world_1, blockPos_1, LOG, LEAF, new ArrayList<>(), blockPos_1);
                }
            }
        }
    }

    public void timber(World world, BlockPos pos, LogBlock logToMatch, LeavesBlock leafToMatch,
            List<BlockPos> alreadyChecked, BlockPos closestLog) {
        List<BlockPos> blockPositionsSurrounding = getBlockPositionsSurrounding(pos);
        blockPositionsSurrounding.removeAll(alreadyChecked);
        for (int x = 0; x < blockPositionsSurrounding.size(); x++) {
            BlockPos blockPos = blockPositionsSurrounding.get(x);
            alreadyChecked.add(blockPos);
            BlockState surroundingBlockState = world.getBlockState(blockPos);
            if (!(surroundingBlockState.getBlock() instanceof AirBlock)) {
                if (surroundingBlockState.getBlock().equals(logToMatch)) {
                    world.breakBlock(blockPos, true);
                    closestLog = blockPos;
                    timber(world, blockPos, logToMatch, leafToMatch, alreadyChecked, closestLog);
                } else if (surroundingBlockState.getBlock().equals(leafToMatch)) {
                    if (closestLog.isWithinDistance(new Vec3i(blockPos.getX(), blockPos.getY(), blockPos.getZ()), 6)) {
                        timber(world, blockPos, logToMatch, leafToMatch, alreadyChecked, closestLog);
                    }
                }
            }
        }
    }

    public List<BlockPos> getBlockPositionsSurrounding(BlockPos pos) {
        List<BlockPos> blockPositionsSurrounding = new ArrayList<>();
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        blockPositionsSurrounding.add(new BlockPos(x - 1, y, z));
        blockPositionsSurrounding.add(new BlockPos(x + 1, y, z));
        blockPositionsSurrounding.add(new BlockPos(x, y, z + 1));
        blockPositionsSurrounding.add(new BlockPos(x, y, z + 1));
        blockPositionsSurrounding.add(new BlockPos(x, y + 1, z));
        return blockPositionsSurrounding;
    }
}
