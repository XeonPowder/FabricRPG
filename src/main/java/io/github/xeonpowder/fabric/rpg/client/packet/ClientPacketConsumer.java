package io.github.xeonpowder.fabric.rpg.client.packet;

import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class ClientPacketConsumer implements PacketConsumer {
    public Identifier id;

    public ClientPacketConsumer(String id) {
        this.id = new Identifier(id);
    }

    @Override
    public void accept(PacketContext context, PacketByteBuf buffer) {

    }
}