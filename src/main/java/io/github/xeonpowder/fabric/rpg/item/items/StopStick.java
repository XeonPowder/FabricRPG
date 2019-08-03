package io.github.xeonpowder.fabric.rpg.item.items;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;

/**
 * StopStick
 */
public class StopStick extends FabricRPGItem {
    public StopStick() {
        super();
        this.setItemName(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName()));
        this.registerItem();
    }
    @Override
    protected List<String> allowedStats() {
        List<String> allowedStats = new ArrayList<String>();
        return allowedStats;
    }
}