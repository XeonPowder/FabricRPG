package io.github.xeonpowder.fabric.rpg.gui.panel;

import java.awt.Color;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;

/**
 * LocationsPanel
 */
public class LocationsPanel<T> extends WPlainPanel {
    public LocationsPanel() {
        super();
        this.setBackgroundPainter((top, left, panel) -> {
            ScreenDrawing.drawGuiPanel(top - 8, left - 8, panel.getWidth() + 14, panel.getHeight() + 14,
                    new Color(100, 100, 100).getRGB());
        });
    }
}