package io.github.xeonpowder.fabric.rpg.server.packet.consumer;

import io.github.xeonpowder.fabric.rpg.server.packet.ServerPacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.PacketByteBuf;

/**
 * PlayerPortalNetworkConsumer
 */
public class PlayerPortalNetworkConsumer extends ServerPacketConsumer{

	public PlayerPortalNetworkConsumer(String id) {
        super("playerportalnetwork");
        this.registerConsumer();
    }
    @Override
    public void accept(PacketContext ctx, PacketByteBuf packetByteBuf) {
        System.out.println("packet recieved");
        CompoundTag playerUuid = packetByteBuf.readCompoundTag();
        playerUuid.putString("portalnetwork", "test");
        packetByteBuf.writeCompoundTag(playerUuid);
        ServerSidePacketRegistry.INSTANCE.sendToPlayer(ctx.getPlayer(), this.id, packetByteBuf);
    }
}