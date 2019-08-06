package io.github.xeonpowder.fabric.rpg.gui.panel;

import java.util.List;

import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.xeonpowder.fabric.rpg.portalnetwork.node.PortalNetworkNode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * PortalNetworkLocationsPanel
 */
public class PortalNetworkLocationsPanel extends LocationsPanel {
    PortalNetworkPanel panel;

    public PortalNetworkLocationsPanel(PortalNetworkPanel portalNetworkPanel, String id,
            List<PortalNetworkNode> nodes) {
        super(id, nodes);
        this.panel = portalNetworkPanel;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void paintBackground(int x, int y) {
        if (!panel.getActivePanel().equals(this) && panel.getChildren().contains(this)) {
            panel.remove(this);
        } else {
            panel.setActivePanel(this);
            panel.add(this, 0, 0, 100, 100);

        }
        super.paintBackground(x, y);
    }

}