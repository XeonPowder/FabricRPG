package io.github.xeonpowder.fabric.rpg.stat.stats;

import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStatInterface;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGStatTypes;
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

    @Override
    public String getStatName() {
        return this.statName;
    }

    public FabricRPGBloodStat copy() {
        return new FabricRPGBloodStat(this);
    }

    @Override
    public Float calculateNewValueForOnLivingEntityDeath(float value) {
        float currentBloodValue = (float) (value + 1);
        // add 1+ 10% of current blood level
        float newBloodValue = (float) (currentBloodValue + (currentBloodValue * .1));

        return newBloodValue;
    }

    @Override
    public float calculateNewValueForOnPlayerAttack(float oldValue) {
        float newValue = (float) (oldValue + 0.1);
        return newValue;
    }

    @Override
    public void registerStatInStatTypeHashMap() {
        FabricRPGStatTypes.fabricRPGStatTypesToClassMap.put(this.getStatName(), this.getClass());
    }

}