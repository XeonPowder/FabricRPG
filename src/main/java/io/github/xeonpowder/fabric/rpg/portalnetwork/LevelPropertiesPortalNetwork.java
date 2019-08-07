package io.github.xeonpowder.fabric.rpg.portalnetwork;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.LevelSyncedComponent;
import net.minecraft.world.level.LevelProperties;

/**
 * LevelPropertiesPortalNetwork
 */
public class LevelPropertiesPortalNetwork extends PortalNetwork implements LevelSyncedComponent {

    public LevelPropertiesPortalNetwork(LevelProperties levelProperties) {
        super(levelProperties);
    }

    @Override
    public ComponentType<?> getComponentType() {
        return FabricRPG.PortalNetworkComponent;
    }

}