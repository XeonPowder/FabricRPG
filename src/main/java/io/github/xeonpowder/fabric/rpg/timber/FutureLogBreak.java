package io.github.xeonpowder.fabric.rpg.timber;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FutureLogBreak {
    private int elapsedTime;
    private int maxTime;
    private World world;
    private BlockPos pos;

    public FutureLogBreak(World world, BlockPos pos, int maxTime) {
        this.world = world;
        this.pos = pos;
        this.maxTime = maxTime;
    }

    int getElapsedTime() {
        return elapsedTime;
    }

    void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    int getMaxTime() {
        return maxTime;
    }

    BlockPos getPos() {
        return pos;
    }

    World getWorld() {
        return world;
    }
}