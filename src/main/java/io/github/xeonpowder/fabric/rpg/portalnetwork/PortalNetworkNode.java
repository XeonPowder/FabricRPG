package io.github.xeonpowder.fabric.rpg.portalnetwork;

import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Position;

/**
 * PortalNetworkNode
 */
public class PortalNetworkNode {

    Position nodePos;
    String translationKey;

    public PortalNetworkNode(Position pos, String translationKey) {
        this.nodePos = pos;
        this.translationKey = translationKey;
    }
    public PortalNetworkNode(double x, double y, double z, String translationKey) {
        this.translationKey = translationKey;
        this.nodePos = new Position(){
        
            @Override
            public double getZ() {
                return x;
            }
        
            @Override
            public double getY() {
                return y;
            }
        
            @Override
            public double getX() {
                return z;
            }
        };
    }
    public Position getPosition() {
        return this.nodePos;
    }
	public TranslatableText getTranslatedText() {
		return new TranslatableText(this.translationKey);
	}
	public String getPositionAsStringForPortalNetworkGui() {
		return "";
	}
    
}