package io.github.xeonpowder.fabric.rpg.client.packet.consumer;

import io.github.xeonpowder.fabric.rpg.client.packet.ClientPacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.PacketByteBuf;

/**
 * ClientPlayerPortalNetworkPacketConsumer
 */
public class ClientPlayerPortalNetworkPacketConsumer extends ClientPacketConsumer {

    public ClientPlayerPortalNetworkPacketConsumer() {
        super("playerportalnetwork");
        this.registerConsumer();
    }
    @Override
    public void accept(PacketContext context, PacketByteBuf packetByteBuf) {
        System.out.println(packetByteBuf.readCompoundTag().asString());
    }
    
}