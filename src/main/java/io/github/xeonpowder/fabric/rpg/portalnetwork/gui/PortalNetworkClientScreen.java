package io.github.xeonpowder.fabric.rpg.portalnetwork.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import net.minecraft.client.MinecraftClient;

/**
 * PortalNetworkClientGui
 */
public class PortalNetworkClientScreen extends ClientCottonScreen {

    public PortalNetworkClientScreen(GuiDescription description) {
        super(description);
    }

    @Override
    public void resize(MinecraftClient client, int screenWidth, int screenHeight) {
        super.init(client, screenWidth, screenHeight);
    }

}