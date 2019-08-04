package io.github.xeonpowder.fabric.rpg.server.packet.consumer;

import io.github.xeonpowder.fabric.rpg.server.packet.ServerPacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.PacketByteBuf;

public class StatPacketConsumer extends ServerPacketConsumer {

    public StatPacketConsumer() {
        super("fabric_rpg_stat_packet");
        this.registerConsumer();
    }

    @Override
    public void accept(PacketContext context, PacketByteBuf buffer) {

    }

}