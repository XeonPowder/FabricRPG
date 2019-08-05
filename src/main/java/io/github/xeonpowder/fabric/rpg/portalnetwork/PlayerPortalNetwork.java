package io.github.xeonpowder.fabric.rpg.portalnetwork;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

/**
 * PlayerPortalNetwork
 */
public class PlayerPortalNetwork extends PortalNetwork<PlayerEntity> implements EntitySyncedComponent {
    public static final ComponentType<PlayerPortalNetwork> PLAYER_ENTITY_PORTAL_NETWORK = ComponentRegistry.INSTANCE
            .registerIfAbsent(new Identifier(FabricRPG.MODID, "player_portal_network"), PlayerPortalNetwork.class);

    public PlayerPortalNetwork(PlayerEntity player) {
        super(player);
    }

    @Override
    public ComponentType<?> getComponentType() {
        return PLAYER_ENTITY_PORTAL_NETWORK;
    }

    @Override
    public Entity getEntity() {
        return getPlayer();
    }

}