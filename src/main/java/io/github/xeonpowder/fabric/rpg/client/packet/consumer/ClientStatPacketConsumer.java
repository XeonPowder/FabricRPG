package io.github.xeonpowder.fabric.rpg.client.packet.consumer;

import io.github.xeonpowder.fabric.rpg.client.packet.ClientPacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.PacketByteBuf;

public class ClientStatPacketConsumer extends ClientPacketConsumer {

    public ClientStatPacketConsumer() {
        super("stat");
        this.registerConsumer();
    }
    @Override
    public void accept(PacketContext context, PacketByteBuf packetByteBuf) {

    }

}