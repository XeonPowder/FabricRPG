package io.github.xeonpowder.fabric.rpg.portalnetwork.node;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.netty.buffer.Unpooled;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.Position;

/**
 * PortalNetworkNode
 */
public class PortalNetworkNode implements PortalNetworkNodeComponent {
    public static final ComponentType<PortalNetworkNodeComponent> PORTAL_NETWORK_NODE = ComponentRegistry.INSTANCE
            .registerIfAbsent(new Identifier(FabricRPG.MODID, "portal_network_node_component"),
                    PortalNetworkNodeComponent.class);
    Position nodePos;
    String translationKey;

    public PortalNetworkNode(Position pos, String translationKey) {
        this.nodePos = pos;
        this.translationKey = translationKey;
    }

    public PortalNetworkNode(double x, double y, double z, String translationKey) {
        this.translationKey = translationKey;
        this.nodePos = new Position() {

            @Override
            public double getZ() {
                return x;
            }

            @Override
            public double getY() {
                return y;
            }

            @Override
            public double getX() {
                return z;
            }
        };
    }

    public Position getPosition() {
        return this.nodePos;
    }

    public TranslatableText getTranslatedText() {
        return new TranslatableText(this.translationKey);
    }

    public String getPositionAsStringForPortalNetworkGui() {
        return String.format("x: %.2f, y: %.2f, z: %.2f", this.getPosition().getX(), this.getPosition().getY(),
                this.getPosition().getZ());
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.nodePos = new Position() {
            @Override
            public double getZ() {
                return tag.getDouble("y");
            }

            @Override
            public double getY() {
                return tag.getDouble("y");
            }

            @Override
            public double getX() {
                return tag.getDouble("x");
            }
        };
        this.translationKey = tag.getString("translationKey");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        tag.putDouble("x", getPosition().getX());
        tag.putDouble("y", getPosition().getY());
        tag.putDouble("z", getPosition().getX());
        tag.putString("translationKey", this.translationKey);
        return tag;
    }

    @Override
    public PortalNetworkNode getNode() {
        return this;
    }

    public static void usePortalNetworkNode(ServerPlayerEntity player, CompoundTag tag) {
        player.teleport(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"));
    }

    @Environment(EnvType.CLIENT)
    public static void usePortalNetworkNode(PortalNetworkNode node) {
        ClientSidePacketRegistry.INSTANCE.sendToServer(new Identifier(FabricRPG.MODID, "use_portal_network_node"),
                new PacketByteBuf(Unpooled.buffer()).writeCompoundTag(node.toTag(new CompoundTag())));
    }

}