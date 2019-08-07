package io.github.xeonpowder.fabric.rpg.stat.stats;

import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStatInterface;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGStatTypes;
import net.minecraft.text.TranslatableText;

public class FabricRPGSoulStat implements FabricRPGItemStackStatInterface {

    private float value;
    public String statName = "soul";

    public FabricRPGSoulStat() {
        this.value = 0;
    }

    public FabricRPGSoulStat(FabricRPGSoulStat stat) {
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

    public FabricRPGSoulStat copy() {
        return new FabricRPGSoulStat(this);
    }

    public Float calculateNewValueForOnLivingEntityDeath(float value) {
        value++;
        return value;
    }

    @Override
    public float calculateNewValueForOnPlayerAttack(float oldValue) {
        float newValue = ((float) oldValue);
        return newValue;
    }

    @Override
    public void registerStatInStatTypeHashMap() {
        FabricRPGStatTypes.fabricRPGStatTypesToClassMap.put(this.getStatName(), this.getClass());
    }

}