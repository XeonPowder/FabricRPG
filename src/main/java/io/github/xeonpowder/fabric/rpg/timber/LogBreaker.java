package io.github.xeonpowder.fabric.rpg.timber;

import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * LogBreaker
 */
public class LogBreaker {

    public static void onBreak(World world, BlockPos pos) {
        // check all nearby leaf blocks
        for (Direction direction : Direction.values()) {
            BlockPos offset = pos.offset(direction, 1);
            BlockState newState = world.getBlockState(offset);

            if (newState.getBlock() instanceof LogBlock) {
                // if the leaf should decay, add it to the break list
                TimberHandler.addFutureBreak(new FutureLogBreak(world, offset, 5));
            }
        }
    }
}