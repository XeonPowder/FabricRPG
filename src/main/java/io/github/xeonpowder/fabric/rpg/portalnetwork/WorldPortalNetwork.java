package io.github.xeonpowder.fabric.rpg.portalnetwork;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.WorldSyncedComponent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

/**
 * WorldPortalNetwork
 */
public class WorldPortalNetwork extends PortalNetwork<World> implements WorldSyncedComponent {

    public static final ComponentType<WorldPortalNetwork> WORLD_PORTAL_NETWORK = ComponentRegistry.INSTANCE
            .registerIfAbsent(new Identifier(FabricRPG.MODID, "world_portal_network"), WorldPortalNetwork.class);

    public WorldPortalNetwork(World world) {
        super(world);
    }

    @Override
    public ComponentType<?> getComponentType() {
        return WORLD_PORTAL_NETWORK;
    }

}