package io.github.xeonpowder.fabric.rpg.portalnetwork;

import java.util.ArrayList;
import java.util.List;

public class PortalNetwork<PlayerEntity> {
    
    private PlayerEntity player;
    private List<PortalNetworkNode> portalNetworkNodes;
    
    public PortalNetwork(PlayerEntity player) {
        this.player = player;
        this.portalNetworkNodes = new ArrayList<>();
    }

	public List<PortalNetworkNode> getNodeHistoryList() {
		return this.portalNetworkNodes;
	}
    
}