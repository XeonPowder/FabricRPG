package io.github.xeonpowder.fabric.rpg.server.packets;

import io.github.xeonpowder.fabric.rpg.server.FabricRPGServerPacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.PacketByteBuf;

public class StatPacketConsumer extends FabricRPGServerPacketConsumer {

    public StatPacketConsumer() {
        super("fabric_rpg_stat_packet");
    }

    @Override
    public void accept(PacketContext context, PacketByteBuf buffer) {

    }

}