package io.github.xeonpowder.fabric.rpg.timber;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * PlayerTimber
 */
public class PlayerTimber extends Timber implements EntitySyncedComponent {

    public PlayerTimber(PlayerEntity playerEntity) {
        super(playerEntity);
    }

    @Override
    public ComponentType<?> getComponentType() {
        return FabricRPG.TIMBER;
    }

    @Override
    public Entity getEntity() {
        return super.getPlayer();
    }

}