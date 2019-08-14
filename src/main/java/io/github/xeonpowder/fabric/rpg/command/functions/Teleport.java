package io.github.xeonpowder.fabric.rpg.command.functions;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

/**
 * Teleport
 */
public class Teleport {

    public static void player2player(ServerPlayerEntity p1, ServerPlayerEntity p2) {
        player2serverWorldPosition(p1, p2.getServerWorld(), p2.getPos(), p2.getCameraEntity().getHeadYaw(), 0.0f);
    }

    public static void player2position(ServerPlayerEntity p1, Vec3d pos, float yaw, float pitch) {
        player2serverWorldPosition(p1, p1.getServerWorld(), pos, yaw, pitch);
    }

    public static void player2serverWorldPosition(ServerPlayerEntity p1, ServerWorld serverWorld, Vec3d pos, float yaw,
            float pitch) {
        p1.teleport(serverWorld, pos.x, pos.y, pos.z, yaw, pitch);
    }
}