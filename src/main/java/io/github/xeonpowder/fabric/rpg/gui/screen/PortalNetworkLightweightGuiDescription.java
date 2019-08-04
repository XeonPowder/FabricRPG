package io.github.xeonpowder.fabric.rpg.gui.screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.TUSK__3.panI18n.FormattingEngine;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPanel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PortalNetworkNode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PortalNetwork;
import io.github.cottonmc.cotton.gui.widget.WButton;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.packet.CustomPayloadC2SPacket;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.world.World;

/**
 * PortalNetworkScreen
 */
public class PortalNetworkLightweightGuiDescription extends LightweightGuiDescription {
    @Environment(EnvType.CLIENT)
    public static PortalNetwork<PlayerEntity> PlayerPortalNetwork;

    @Environment(EnvType.SERVER)
    public static HashMap<String, PortalNetwork<PlayerEntity>> ServerMasterPortalNetwork = new HashMap<>();

    public PortalNetworkLightweightGuiDescription(World world, PlayerEntity player) {
        super();
        if (world.isClient) {
            PlayerPortalNetwork = new PortalNetwork<PlayerEntity>(player);
            // CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(new Identifier(FabricRPG.MODID, ""), new PacketByteBuf(ByteBufUtil.encodeString(new ByteBuf, player.getUuid().toString(), charset)));
            CompoundTag request = new CompoundTag();
            request.putString("playerUuid", player.getUuid().toString());
            CustomPayloadC2SPacket packet = new CustomPayloadC2SPacket(new Identifier(FabricRPG.MODID, "playerportalnetwork"), new PacketByteBuf(Unpooled.buffer()).writeCompoundTag(request));
            ClientSidePacketRegistry.INSTANCE.sendToServer(packet); 
            WPlainPanel rootPanel = new WPlainPanel();
            setRootPanel(rootPanel);
            rootPanel.setSize(MinecraftClient.getInstance().window.getWidth(), MinecraftClient.getInstance().window.getHeight());
            rootPanel.setLocation(0, 0);
            WPlainPanel locationsPanel = new WPlainPanel();
            //lazy load past query of nodes in the teleport network
            List<PortalNetworkNode> playerNodeHistoryList = PlayerPortalNetwork.getNodeHistoryList();
            if (playerNodeHistoryList.size() != 0) {
                playerNodeHistoryList.forEach(portalNetworkNode -> {
                    WPlainPanel locationPanel = new WPlainPanel();
                    WLabel locationLabel = new WLabel(
                        new LiteralText(
                            FormattingEngine.replaceColorCodeEnumInString(
                                portalNetworkNode.getTranslatedText().asString()
                            )
                        ),
                        WLabel.DEFAULT_TEXT_COLOR
                    );
                    WLabel locationPosition = new WLabel(
                        new LiteralText(
                            FormattingEngine.replaceColorCodeEnumInString(
                                portalNetworkNode.getPositionAsStringForPortalNetworkGui()
                            )
                        ),
                        WLabel.DEFAULT_TEXT_COLOR
                    );
                    locationPanel.add(locationLabel, (int)(locationPanel.getWidth()*.1), (int)(locationPanel.getHeight()*.1));
                    locationPanel.add(locationPosition, (int)(locationPanel.getWidth()*.1), (int)(locationPanel.getHeight()*.5));
                    locationsPanel.add(locationPanel, (int)(locationsPanel.getWidth() * .1), playerNodeHistoryList.indexOf(portalNetworkNode) * locationPanel.getHeight());
                });
            }
            //query teleport network using hashCode of past query -- teleport network compares hashCode to see if new information needs to be queried.
            WPlainPanel titlePanel = new WPlainPanel();
            titlePanel.setSize(MinecraftClient.getInstance().window.getWidth(), (int)(MinecraftClient.getInstance().window.getHeight()*.15));
            // Title bar.
            WLabel titleLabel = new WLabel(
                new LiteralText(FormattingEngine.replaceColorCodeEnumInString((new TranslatableText("gui.fabric_rpg.portal_network").asString()))), 
                WLabel.DEFAULT_TEXT_COLOR
            );
            titlePanel.add(titleLabel, ((rootPanel.getWidth()/2)), 0);
            System.out.printf("root\nwidth: %s\nheight: %s\nabs x: %s\nabs y: %s", rootPanel.getWidth(), rootPanel.getHeight(), rootPanel.getAbsoluteX(), rootPanel.getAbsoluteY());
            System.out.printf("window width: %s. window height: %s.", MinecraftClient.getInstance().window.getWidth(), MinecraftClient.getInstance().window.getHeight());
            System.out.println((-1 * (int)(Math.ceil(
                        ((double)MinecraftClient.getInstance().window.getWidth())/2
                    ))));
            
            rootPanel.add(titlePanel, 0, 0);
            rootPanel.add(locationsPanel, 0, (int)(titlePanel.getHeight() * 1.1));
            // rootPanel.add(title,
            //     (-1 * (int)(Math.ceil(
            //         ((double)MinecraftClient.getInstance().window.getWidth())/2
            //     ))), 
            //     (-1 * (int)(Math.ceil(
            //         ((double)MinecraftClient.getInstance().window.getHeight())/2
            //     ) - title.getHeight()))
            // );
            rootPanel.validate(this);
        }   
    }
}