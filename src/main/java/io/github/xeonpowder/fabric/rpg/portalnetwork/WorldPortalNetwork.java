package io.github.xeonpowder.fabric.rpg.portalnetwork;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.WorldSyncedComponent;
import net.minecraft.world.World;

/**
 * WorldPortalNetwork
 */
public class WorldPortalNetwork extends PortalNetwork implements WorldSyncedComponent {

    public WorldPortalNetwork(World world) {
        super(world);
    }

    @Override
    public ComponentType<?> getComponentType() {
        return FabricRPG.PortalNetworkComponent;
    }

}