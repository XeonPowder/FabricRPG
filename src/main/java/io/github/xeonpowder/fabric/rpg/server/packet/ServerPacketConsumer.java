package io.github.xeonpowder.fabric.rpg.server.packet;

import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class ServerPacketConsumer implements PacketConsumer {

    public Identifier id;

    public ServerPacketConsumer(String id) {
        this.id = new Identifier(id);
    }

    public void registerConsumer() {
        System.out.println(this.id.toString());
        ServerSidePacketRegistry.INSTANCE.register(this.id, (ctx, buffer) -> {
            this.accept(ctx, buffer);
        });
    }
    
    @Override
    public void accept(PacketContext context, PacketByteBuf buffer) {

    }

}