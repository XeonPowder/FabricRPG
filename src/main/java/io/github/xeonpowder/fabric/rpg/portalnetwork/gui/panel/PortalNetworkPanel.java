package io.github.xeonpowder.fabric.rpg.portalnetwork.gui.panel;

import java.util.List;

import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;

/**
 * PortalNetworkPanel
 */
public class PortalNetworkPanel extends WPlainPanel {
    PortalNetworkLocationsPanel activePanel;
    List<PortalNetworkLocationsPanel> panels;

    public PortalNetworkPanel() {
        super();
    }

    public void addPanel(PortalNetworkLocationsPanel panel) {
        panels.add(panel);
    }

    public List<PortalNetworkLocationsPanel> getPanels() {
        return this.panels;
    }

    public void setActivePanel(PortalNetworkLocationsPanel panel) {
        this.activePanel = panel;
    }

    public PortalNetworkLocationsPanel getActivePanel() {
        return this.activePanel;
    }

    public List<WWidget> getChildren() {
        return this.children;
    }

}