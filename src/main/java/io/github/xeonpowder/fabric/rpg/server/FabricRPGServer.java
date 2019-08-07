package io.github.xeonpowder.fabric.rpg.server;

import net.fabricmc.api.DedicatedServerModInitializer;

/**
 * FabricRPGServer
 */
public class FabricRPGServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        // new
        // ServerPacketConsumerRegistrator("io.github.xeonpowder.fabric.rpg.server.packet.consumer");
        System.out.println("server initialized");
    }

}