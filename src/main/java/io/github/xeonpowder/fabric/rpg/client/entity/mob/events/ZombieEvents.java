package io.github.xeonpowder.fabric.rpg.client.entity.mob.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public class ZombieEvents {
    public interface DeathByPlayerCallback {
        Event<DeathByPlayerCallback> EVENT = EventFactory.createArrayBacked(DeathByPlayerCallback.class,
                (listeners) -> (player, sheep) -> {
                    for (DeathByPlayerCallback event : listeners) {
                        ActionResult result = event.interact(player, sheep);
                        if (result != ActionResult.PASS) {
                            return result;
                        }
                    }

                    return ActionResult.PASS;
                });

        ActionResult interact(PlayerEntity player, ZombieEntity zombie);
    }

    public interface DeathByEnvironmentCallback {
        Event<DeathByEnvironmentCallback> EVENT = EventFactory.createArrayBacked(DeathByEnvironmentCallback.class,
                (listeners) -> (player, sheep) -> {
                    for (DeathByEnvironmentCallback event : listeners) {
                        ActionResult result = event.interact(player, sheep);
                        if (result != ActionResult.PASS) {
                            return result;
                        }
                    }

                    return ActionResult.PASS;
                });

        ActionResult interact(PlayerEntity player, ZombieEntity zombie);
    }
}