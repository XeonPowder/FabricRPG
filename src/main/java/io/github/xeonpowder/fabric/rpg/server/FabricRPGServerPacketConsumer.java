package io.github.xeonpowder.fabric.rpg.server;

import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class FabricRPGServerPacketConsumer implements PacketConsumer {

    public Identifier id;

    public FabricRPGServerPacketConsumer(String id) {
        this.id = new Identifier(id);
    }

    @Override
    public void accept(PacketContext context, PacketByteBuf buffer) {

    }

}