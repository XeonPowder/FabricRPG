package io.github.xeonpowder.fabric.rpg.portalnetwork;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * PlayerPortalNetwork
 */
public class PlayerPortalNetwork extends PortalNetwork implements EntitySyncedComponent {

    public PlayerPortalNetwork(PlayerEntity player) {
        super(player);
    }

    @Override
    public ComponentType<?> getComponentType() {
        return FabricRPG.PortalNetworkComponent;
    }

    @Override
    public Entity getEntity() {
        return getPlayer();
    }

}