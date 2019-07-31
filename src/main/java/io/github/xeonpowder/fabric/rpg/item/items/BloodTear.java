package io.github.xeonpowder.fabric.rpg.item.items;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;

public class BloodTear extends FabricRPGItem {
    public BloodTear() {
        super();
        this.setItemName(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName()));
        this.registerItem();
    }

}