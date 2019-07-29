package io.github.xeonpowder.fabric.rpg.stat.stats;

import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStatInterface;
import net.minecraft.text.TranslatableText;

public class FabricRPGBloodStat implements FabricRPGItemStackStatInterface {

    private float value;
    public String statName = "blood";

    public FabricRPGBloodStat() {
        this.value = 0;
    }

    public FabricRPGBloodStat(FabricRPGBloodStat stat) {
        this.value = stat.value;
    }

    @Override
    public TranslatableText getNameAsTranslatedText() {
        return new TranslatableText("custom_stat.fabric_rpg." + this.statName + "tooltip");
    }

    @Override
    public float getStatValue() {
        return this.value;
    }

    @Override
    public void setStatValue(float value) {
        this.value = value;
    }

    public String getStatName() {
        return this.statName;
    }

    public FabricRPGBloodStat copy() {
        return new FabricRPGBloodStat(this);
    }

}