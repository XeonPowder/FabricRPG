package io.github.xeonpowder.fabric.rpg.profession.gui.panel;

import java.awt.Color;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGPlayerProfessions;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGPlayerProfessionsComponent;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession.Profession;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

/**
 * ProfessionPanel
 */
public class ProfessionPanel extends WPlainPanel {
    private PlayerEntity player;
    private FabricRPGProfession profession;

    public ProfessionPanel(PlayerEntity player, FabricRPGProfession profession, int width, int height, int index) {
        this.player = player;
        this.profession = profession;
        this.add(new WLabel(profession.getName()),
                (width / 2) - (MinecraftClient.getInstance().textRenderer.getStringWidth(profession.getName()) / 2),
                (int) (height * .1 + (height * .2 * index)));
        this.setBackgroundPainter((top, left, panel) -> {
            ScreenDrawing.drawGuiPanel(top - 8, left - 8, panel.getWidth() + 14, panel.getHeight() + 14,
                    new Color(100, 100, 100).getRGB());
        });
    }

    @Override
    public void onClick(int x, int y, int button) {
        System.out.println("Clicked");
        FabricRPGPlayerProfessionsComponent comp = FabricRPG.PlayerProfessionsComponent.get(this.player);
        System.out.println(comp);
        FabricRPGPlayerProfessions professions = comp.getPlayerProfessions();
        System.out.println(professions);
        FabricRPGProfession profession = FabricRPG.PROFESSION_LIST.get(Profession.ID.MINING)
                .get(this.profession.getNameKey());
        System.out.println(profession);
        System.out.println(profession.getName());
        System.out.println(this.profession);
        System.out.println(this.profession.getName());
        professions.addProfession(profession);
        super.onClick(x, y, button);
    }
}