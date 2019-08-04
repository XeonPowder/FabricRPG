package io.github.xeonpowder.fabric.rpg.server.packet.consumer;

import io.github.xeonpowder.fabric.rpg.server.packet.ServerPacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.PacketByteBuf;

public class StatPacketConsumer extends ServerPacketConsumer {

    public StatPacketConsumer() {
        super("stat");
        this.registerConsumer();
    }

    @Override
    public void accept(PacketContext context, PacketByteBuf buffer) {

    }

}