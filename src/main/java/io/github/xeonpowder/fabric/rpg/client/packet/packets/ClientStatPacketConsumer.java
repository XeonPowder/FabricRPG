package io.github.xeonpowder.fabric.rpg.client.packet.packets;

import io.github.xeonpowder.fabric.rpg.server.FabricRPGServerPacketConsumer;

public class ClientStatPacketConsumer extends FabricRPGServerPacketConsumer {

    public ClientStatPacketConsumer() {
        super("fabric_rpg_stat_packet");
    }

}