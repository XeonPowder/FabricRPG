package io.github.xeonpowder.fabric.rpg.client;

import io.github.xeonpowder.fabric.rpg.block.blocks.plant.PortalPlantBlock;
import io.github.xeonpowder.fabric.rpg.client.packet.PacketConsumerRegistrator;
import io.github.xeonpowder.fabric.rpg.gui.PortalNetworkClientScreen;
import io.github.xeonpowder.fabric.rpg.gui.screen.PortalNetworkLightweightGuiDescription;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.util.Identifier;

/**
 * FabricRPG
 */
public class FabricRPGClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        new PacketConsumerRegistrator();
    }

    
}