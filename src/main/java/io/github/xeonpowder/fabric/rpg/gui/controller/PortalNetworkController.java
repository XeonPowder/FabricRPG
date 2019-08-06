package io.github.xeonpowder.fabric.rpg.gui.controller;

import io.github.cottonmc.cotton.gui.CottonScreenController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

/**
 * PortalNetworkController
 */
public class PortalNetworkController extends CottonScreenController {

    public PortalNetworkController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context),
                getBlockPropertyDelegate(context));
        WGridPanel rootPanel = (WGridPanel) getRootPanel();

        rootPanel.add(new WLabel(
                new LiteralText(FormattingEngine.replaceColorCodeEnumInString(
                        (new TranslatableText("gui.fabric_rpg.portal_network").asString()))),
                WLabel.DEFAULT_TEXT_COLOR), 0, 0);

        WItemSlot inputSlot = WItemSlot.of(blockInventory, 0);
        rootPanel.add(inputSlot, 4, 1);

        rootPanel.add(this.createPlayerInventoryPanel(), 0, 3);

        rootPanel.validate(this);
    }

    @Override
    public int getCraftingResultSlotIndex() {
        return -1;
    }

}