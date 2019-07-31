package io.github.xeonpowder.fabric.rpg.stat.stats;

import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStatInterface;
import net.minecraft.text.TranslatableText;

public class FabricRPGLifeStealStat implements FabricRPGItemStackStatInterface {

    private float value;
    public String statName = "life_steal";

    public FabricRPGLifeStealStat() {
        this.value = 0;
    }

    public FabricRPGLifeStealStat(FabricRPGLifeStealStat stat) {
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

    @Override
    public String getStatName() {
        return this.statName;
    }

    public FabricRPGLifeStealStat copy() {
        return new FabricRPGLifeStealStat(this);
    }

    public Float calculateNewValueForOnLivingEntityDeath(float value) {
        return value;
    }

    @Override
    public float calculateNewValueForOnPlayerAttack(float oldValue) {
        float newValue = ((float) oldValue);
        return newValue;
    }

}