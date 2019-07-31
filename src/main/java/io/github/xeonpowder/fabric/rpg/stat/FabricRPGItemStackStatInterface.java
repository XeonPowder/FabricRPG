package io.github.xeonpowder.fabric.rpg.stat;

import net.minecraft.text.TranslatableText;

public interface FabricRPGItemStackStatInterface {
    public String statName = "";
    public float value = 0;

    public TranslatableText getNameAsTranslatedText();

    public float getStatValue();

    public String getStatName();

    public void setStatValue(float value);

    public Float calculateNewValueForOnLivingEntityDeath(float value);

    public float calculateNewValueForOnPlayerAttack(float oldValue);

}