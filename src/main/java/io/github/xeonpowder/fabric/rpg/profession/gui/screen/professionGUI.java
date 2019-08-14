package io.github.xeonpowder.fabric.rpg.profession.gui.screen;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGPlayerProfessions;
import io.github.xeonpowder.fabric.rpg.profession.gui.panel.ProfessionsPanel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

/**
 * professionGUI
 */
public class professionGUI extends LightweightGuiDescription {
    public professionGUI(World world, PlayerEntity player) {
        super();
        if (world.isClient) {
            WPlainPanel rootPanel = new WPlainPanel();
            this.setRootPanel(rootPanel);
            Window window = MinecraftClient.getInstance().window;
            FabricRPGPlayerProfessions playerProfessions = FabricRPG.PlayerProfessionsComponent.get(player)
                    .getPlayerProfessions();
            ProfessionsPanel professionsPanel = new ProfessionsPanel(player, playerProfessions, window.getScaledWidth(),
                    window.getScaledHeight());
            professionsPanel.addToRootPanel(rootPanel, 0, 0);
            rootPanel.setBackgroundPainter(BackgroundPainter.VANILLA);
            rootPanel.validate(this);
        }
    }
}