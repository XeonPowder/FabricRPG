package io.github.xeonpowder.fabric.rpg.gui.screen;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import io.github.TUSK__3.panI18n.FormattingEngine;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PortalNetwork;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PortalNetworkNode;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
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
            ClientSidePacketRegistry.INSTANCE.sendToServer(new Identifier(FabricRPG.MODID, "playerportalnetwork"), new PacketByteBuf(Unpooled.buffer()).writeCompoundTag(request)); 
            WPlainPanel rootPanel = new WPlainPanel();
            setRootPanel(rootPanel);
            Window window = MinecraftClient.getInstance().window;
            rootPanel.setSize((int)(window.getScaledWidth()*.66), (int)(window.getScaledHeight() *.66));
            rootPanel.setLocation((int)(window.getScaledWidth()*.17), (int)(window.getScaledHeight()*.17));
            rootPanel.setBackgroundPainter(BackgroundPainter.VANILLA);
            rootPanel.paintBackground(0, rootPanel.getHeight());
            WPlainPanel locationsPanel = new WPlainPanel();
            //lazy load past query of nodes in the teleport network
            List<PortalNetworkNode> playerNodeHistoryList = PlayerPortalNetwork.getNodeHistoryList();
            playerNodeHistoryList.add(new PortalNetworkNode(player.getPos().getX(), player.getPos().getY(), player.getPos().getY(), "test"));
            if (playerNodeHistoryList.size() != 0) {
                playerNodeHistoryList.forEach(portalNetworkNode -> {
                    Position nodePosition = portalNetworkNode.getPosition();
                    WPlainPanel locationPanel = new WPlainPanel(){
                        Color targetColor = new Color(250, 0, 0);
                        Color currentColor = new Color(0xFF2F2F2F);
                        Color defaultColor = new Color(0xFF2F2F2F);
                        boolean error, redReached, blueReached, greenReached = false;
                        @Environment(EnvType.CLIENT)
                        @Override
                        public void paintBackground(int x, int y) {
                            if (error) {
                                int targetRed = targetColor.getRed();
                                int targetBlue = targetColor.getBlue();
                                int targetGreen = targetColor.getGreen();
                                int currentRed = currentColor.getRed();
                                int currentBlue = currentColor.getBlue();
                                int currentGreen = currentColor.getGreen();
                                if (!redReached) {
                                    if(targetRed > currentRed) {
                                        currentRed++;
                                    } else if (targetRed < currentRed){
                                        currentRed--;
                                    } else {
                                        redReached = true;
                                    }
                                }
                                if (!blueReached) {
                                    if (targetBlue > currentBlue) {
                                        currentBlue++;
                                    } else if(currentBlue > targetBlue){
                                        currentBlue--;
                                    } else {
                                        blueReached = true;
                                    }
                                }
                                if (!greenReached) {
                                    if (targetGreen > currentGreen) {
                                        currentGreen++;
                                    } else if (currentGreen > targetGreen){
                                        currentGreen--;
                                    } else {
                                        greenReached = true;
                                    }
                                }
                                if (redReached && blueReached && greenReached) {
                                    redReached = blueReached = greenReached = false;
                                    if (targetColor.getRed() != defaultColor.getRed() && targetColor.getBlue() != defaultColor.getBlue() && targetColor.getGreen() != defaultColor.getGreen()) {
                                        targetColor = defaultColor;
                                    } else {
                                        error = false;
                                        this.setBackgroundPainter((left, top, panel) -> {
                                            ScreenDrawing.drawGuiPanel(left-8, top-8, panel.getWidth()+14, panel.getHeight()+14, this.defaultColor.getRGB());
                                        });
                                    }
                                } else {
                                    this.currentColor = new Color(currentRed, currentBlue, currentGreen);
                                    this.setBackgroundPainter((left, top, panel) -> {
                                        ScreenDrawing.drawGuiPanel(left-8, top-8, panel.getWidth()+14, panel.getHeight()+14, this.currentColor.getRGB());
                                    });
                                }
                            }
                            super.paintBackground(x, y);
                        }
                        @Override
                        public void onClick(int x, int y, int button) {
                            int requiredCurrencyAmount = calculateRequiredCurrency(player.getPos(), nodePosition);
                            if (hasEnoughCurrencyToTeleport(player.inventory, requiredCurrencyAmount)) {
                                removeCurrencyFromPlayerInventory(player.inventory, requiredCurrencyAmount);
                                player.teleport(nodePosition.getX(), nodePosition.getY(), nodePosition.getZ());
                            } else {
                                this.error = true;
                                
                            }
                            
                        }
                    };
                    locationPanel.setBackgroundPainter((left, top, panel) -> {
                        ScreenDrawing.drawGuiPanel(left-8, top-8, panel.getWidth()+14, panel.getHeight()+14, 0xFF2F2F2F);
                    });
                    locationPanel.paintBackground(0, locationPanel.getHeight());
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
            titlePanel.setSize(rootPanel.getWidth(), (int)(rootPanel.getHeight()*.15));
            titlePanel.setLocation(0, 0);
            // Title bar.
            LiteralText titleLabelText = new LiteralText(FormattingEngine.replaceColorCodeEnumInString((new TranslatableText("gui.fabric_rpg.portal_network").asString())));
            WLabel titleLabel = new WLabel(
                titleLabelText, 
                WLabel.DEFAULT_TEXT_COLOR
            );
            titlePanel.add(titleLabel, ((rootPanel.getWidth()/2) - MinecraftClient.getInstance().textRenderer.getStringWidth(titleLabelText.asString())/2), (int)(titlePanel.getHeight()*.5)); 
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

    protected int calculateRequiredCurrency(Vec3d pos, Position nodePosition) {
        return ((int)(pos.distanceTo(new Vec3d(nodePosition.getX(), nodePosition.getY(), nodePosition.getZ()))/100));
    }

	protected void removeCurrencyFromPlayerInventory(PlayerInventory inventory, int countToRemove) {
        
        inventory.takeInvStack(inventory.main.indexOf(inventory.main.stream().filter(itemStack -> {
            return itemStack.getTranslationKey() == "";
        }).collect(Collectors.toList()).get(0)), countToRemove);
    }

    protected boolean hasEnoughCurrencyToTeleport(PlayerInventory inventory, int requiredCount) {
        return inventory.countInInv(Registry.ITEM.get(new Identifier(FabricRPG.MODID, "portal_flower_block"))) >= requiredCount;
    }

	public void queryServerForPlayerPortalNetwork() {

    }
}