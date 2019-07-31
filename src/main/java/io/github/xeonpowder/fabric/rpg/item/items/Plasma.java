package io.github.xeonpowder.fabric.rpg.item.items;

import com.google.common.base.CaseFormat;
import java.util.ArrayList;
import java.util.List;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;

public class Plasma extends FabricRPGItem {
    public Plasma() {
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