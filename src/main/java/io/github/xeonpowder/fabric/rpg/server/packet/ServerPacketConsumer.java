package io.github.xeonpowder.fabric.rpg.server.packet;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class ServerPacketConsumer implements PacketConsumer {

    public Identifier id;

    public ServerPacketConsumer(String id) {
        this.id = new Identifier(FabricRPG.MODID, id);
    }

    public void registerConsumer() {
        System.out.println(this.id.toString());
        ServerSidePacketRegistry.INSTANCE.register(this.id, this);
    }
    
    @Override
    public void accept(PacketContext context, PacketByteBuf buffer) {

    }

}