package io.github.xeonpowder.fabric.rpg.profession.gui.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGPlayerProfessions;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession.Profession;
import net.minecraft.entity.player.PlayerEntity;

/**
 * ProfessionsPanel
 */
public class ProfessionsPanel extends WPlainPanel {
    FabricRPGPlayerProfessions playerProfessions;

    public ProfessionsPanel(PlayerEntity player, FabricRPGPlayerProfessions playerProfessions, int width, int height) {
        this.playerProfessions = playerProfessions;
        HashMap<Profession.ID, FabricRPGProfession> professions = playerProfessions.getProfessions();
        List<FabricRPGProfession> professionsList = new ArrayList<>();
        professions.values().forEach(profession -> {
            professionsList.add(profession);
        });
        if (professionsList.size() == 0) {
            this.playerProfessions.getAvailableProfessions().values().forEach(profession -> {
                professionsList.add(profession);
            });
        }
        this.setSize(width, height);
        professionsList.forEach(profession -> {
            addProfessionPanel(
                    new ProfessionPanel(player, profession, width, height, professionsList.indexOf(profession)),
                    (int) (width * .1), (int) (height * .1 + (height * .2 * professionsList.indexOf(profession))),
                    (int) (width * .8), (int) (height * .2));
        });
    }

    public void addProfessionPanel(ProfessionPanel panel, int x, int y, int width, int height) {
        this.add(panel, x, y, width, height);
    }

    public void addToRootPanel(WPlainPanel rootPanel, int x, int y) {
        rootPanel.add(this, x, y, this.getWidth(), this.getHeight());
    }

}