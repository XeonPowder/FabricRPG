package io.github.xeonpowder.fabric.rpg.client.packet;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class ClientPacketConsumer implements PacketConsumer {
    public Identifier id;

    public ClientPacketConsumer(String id) {
        this.id = new Identifier(FabricRPG.MODID, id);

    }

    public void registerConsumer() {
        // System.out.println(this.id.toString());
        ClientSidePacketRegistry.INSTANCE.register(this.id, this);
    }

    @Override
    public void accept(PacketContext context, PacketByteBuf buffer) {
        return;
    }
}