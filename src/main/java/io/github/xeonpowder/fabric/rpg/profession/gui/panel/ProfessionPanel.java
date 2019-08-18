package io.github.xeonpowder.fabric.rpg.profession.gui.panel;

import java.awt.Color;
import com.ibm.icu.util.Currency;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGPlayerProfessions;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGPlayerProfessionsComponent;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;

/**
 * ProfessionPanel
 */
public class ProfessionPanel extends WPlainPanel {
    private PlayerEntity player;
    private FabricRPGProfession profession;

    public ProfessionPanel(PlayerEntity player, FabricRPGProfession profession, int width,
            int height, int index) {
        this.player = player;
        this.profession = profession;
        this.add(new WLabel(profession.getName()),
                (width / 4) - (MinecraftClient.getInstance().textRenderer
                        .getStringWidth(profession.getName()) / 2),
                (int) (height * .075 + (height * .2 * index)));
        this.setBackgroundPainter((top, left, panel) -> {
            ScreenDrawing.drawGuiPanel(top - 8, left - 8, panel.getWidth() + 14,
                    panel.getHeight() + 14, new Color(100, 100, 100).getRGB());
        });
    }

    @Override
    public void onClick(int x, int y, int button) {
        FabricRPGPlayerProfessionsComponent comp =
                FabricRPG.PlayerProfessionsComponent.get(this.player);
        FabricRPGPlayerProfessions professions = comp.getPlayerProfessions();
        FabricRPGProfession currentProfession =
                professions.getProfession(this.profession.getProfessionID());
        if (currentProfession != null) {
            if (currentProfession.getNameKey().equals(this.profession.getNameKey())) {
                this.player.addChatMessage(new LiteralText("You already have this profession."),
                        true);
            } else if (currentProfession.hasNextProfession() && currentProfession
                    .getNextProfession().getNameKey().equals(this.profession.getNameKey())) {
                if (currentProfession.getLevel() > 50) {
                    professions.removeProfession(currentProfession);
                    professions.addProfession(this.profession);
                    this.player.addChatMessage(new LiteralText(
                            "You have learned a new profession: " + this.profession.getName()),
                            true);
                }
            }
        } else {
            professions.addProfession(this.profession);
        }
        super.onClick(x, y, button);
    }
}
