package io.github.xeonpowder.fabric.rpg.stat.stats;

import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStatInterface;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGStatTypes;
import net.minecraft.text.TranslatableText;

public class FabricRPGAttackSpeedStat implements FabricRPGItemStackStatInterface {

    private float value;
    public String statName = "attack_speed";

    public FabricRPGAttackSpeedStat() {
        this.value = 0;
    }

    public FabricRPGAttackSpeedStat(FabricRPGAttackSpeedStat stat) {
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

    public FabricRPGAttackSpeedStat copy() {
        return new FabricRPGAttackSpeedStat(this);
    }

    public Float calculateNewValueForOnLivingEntityDeath(float value) {
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