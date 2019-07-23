package io.github.xeonpowder.fabric.rpg.client.entity.passive.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public class SheepEvents {
    public interface OnDeathCallback {
        Event<OnDeathCallback> EVENT = EventFactory.createArrayBacked(OnDeathCallback.class,
                (listeners) -> (damageSource) -> {
                    for (OnDeathCallback event : listeners) {
                        ActionResult result = event.interact(damageSource);
                        if (result != ActionResult.PASS) {
                            return result;
                        }
                    }

                    return ActionResult.PASS;
                });

        ActionResult interact(DamageSource damageSource);
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

        ActionResult interact(PlayerEntity player, SheepEntity sheep);
    }

    public interface SheepShearCallback {
        Event<SheepShearCallback> EVENT = EventFactory.createArrayBacked(SheepShearCallback.class,
                (listeners) -> (player, sheep) -> {
                    for (SheepShearCallback event : listeners) {
                        ActionResult result = event.interact(player, sheep);
                        if (result != ActionResult.PASS) {
                            return result;
                        }
                    }

                    return ActionResult.PASS;
                });

        ActionResult interact(PlayerEntity player, SheepEntity sheep);
    }
}