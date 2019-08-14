package io.github.xeonpowder.fabric.rpg.portalnetwork.gui.panel;

import java.awt.Color;
import java.util.List;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.portalnetwork.node.PortalNetworkNode;
import net.minecraft.util.Identifier;

/**
 * LocationsPanel
 */
public class LocationsPanel extends WPlainPanel {
    private List<PortalNetworkNode> nodes;
    private Identifier id;

    public LocationsPanel(String identifier, List<PortalNetworkNode> portalNodes) {
        super();
        this.id = new Identifier(FabricRPG.MODID, identifier);
        this.nodes = portalNodes;
        this.setBackgroundPainter((top, left, panel) -> {
            ScreenDrawing.drawGuiPanel(top - 8, left - 8, panel.getWidth() + 14, panel.getHeight() + 14,
                    new Color(100, 100, 100).getRGB());
        });
    }

    public List<PortalNetworkNode> getNodes() {
        return this.nodes;
    }

    public Identifier getIdentifier() {
        return this.id;
    }
}