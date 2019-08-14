package io.github.xeonpowder.fabric.rpg.profession.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import io.github.xeonpowder.fabric.rpg.profession.gui.screen.professionGUI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

/**
 * PortalNetworkClientGui
 */
public class ProfessionClientScreen extends ClientCottonScreen {

    public ProfessionClientScreen(GuiDescription description) {
        super(description);
    }

    public ProfessionClientScreen(World world, PlayerEntity player) {
        super(new professionGUI(world, player));
    }

    @Override
    public void resize(MinecraftClient client, int screenWidth, int screenHeight) {
        super.init(client, screenWidth, screenHeight);
    }

}