package io.github.xeonpowder.fabric.rpg.items;

public interface FabricRPGItemStackStat {
    public static String getNameAsTranslatedText(FabricRPGItemStackStat stat) {
        return stat.getNameAsTranslatedText();
    }

    public static float getValue(FabricRPGItemStackStat stat) {
        return stat.getValue();
    }

    public static void setValue(FabricRPGItemStackStat stat, float value) {
        stat.setValue(value);
    }

    public String getNameAsTranslatedText();

    public float getValue();

    public void setValue(float value);
}