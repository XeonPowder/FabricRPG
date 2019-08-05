package io.github.xeonpowder.fabric.rpg.portalnetwork;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.LevelSyncedComponent;
import net.minecraft.util.Identifier;
import net.minecraft.world.level.LevelProperties;

/**
 * LevelPropertiesPortalNetwork
 */
public class LevelPropertiesPortalNetwork extends PortalNetwork<LevelProperties> implements LevelSyncedComponent {
    public static final ComponentType<LevelPropertiesPortalNetwork> LEVEL_PROPERTIES_PORTAL_NETWORK = ComponentRegistry.INSTANCE
            .registerIfAbsent(new Identifier(FabricRPG.MODID, "level_properties_portal_network"),
                    LevelPropertiesPortalNetwork.class);

    public LevelPropertiesPortalNetwork(LevelProperties levelProperties) {
        super(levelProperties);
    }

    @Override
    public ComponentType<?> getComponentType() {
        return LEVEL_PROPERTIES_PORTAL_NETWORK;
    }

}