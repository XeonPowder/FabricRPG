package io.github.xeonpowder.fabric.rpg.client;

import io.github.xeonpowder.fabric.rpg.client.packet.ClientPacketConsumerRegistrator;
import net.fabricmc.api.ClientModInitializer;

/**
 * FabricRPG
 */
public class FabricRPGClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        new ClientPacketConsumerRegistrator("io.github.xeonpowder.fabric.rpg.client.packet.consumer");
    }

    
}