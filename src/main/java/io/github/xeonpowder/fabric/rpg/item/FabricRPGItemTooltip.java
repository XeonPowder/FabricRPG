package io.github.xeonpowder.fabric.rpg.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.github.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStatInterface;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class FabricRPGItemTooltip {

    public static List<Text> wrapAndTranslateKeyToTextList(String key, MinecraftClient client) {
        return wrapAndTranslateKeyToTextList(key, client, new ArrayList<Text>());
    }

    public static List<Text> wrapAndTranslateKeyToTextList(String key, MinecraftClient client,
            List<Text> tooltipTextList) {
        List<Text> wrappedLocalizedTextAsTextList = tooltipTextList;
        wrapAndTranslateKeyToStringList(key, client)
                .forEach(string -> wrappedLocalizedTextAsTextList.add(new LiteralText(string)));
        return wrappedLocalizedTextAsTextList;
    }

    public static List<String> wrapAndTranslateKeyToStringList(String key, MinecraftClient client) {
        return FormattingEngine.wrapStringToWidth(new TranslatableText(key).asString(), // string
                70, // wrapWidth
                (c) -> (int) client.textRenderer.getCharWidth((char) c), // character width function
                Locale.forLanguageTag(client.getLanguageManager().getLanguage().getCode()), // locale of
                                                                                            // minecraft
                                                                                            // client
                FormattingEngine.COLOR_CODE.WHITE);// default color
                                                   // code
    }

    public static List<Text> wrapAndTranslateStatsToTextList(MinecraftClient client,
            HashMap<String, FabricRPGItemStackStatInterface> stats, List<Text> tooltipTextList) {
        List<Text> wrappedLocalizedTextWithStatsAsTextList = tooltipTextList;
        wrapAndTranslateStatsToStringList(client, stats)
                .forEach(string -> wrappedLocalizedTextWithStatsAsTextList.add(new LiteralText(string)));
        return wrappedLocalizedTextWithStatsAsTextList;
    }

    public static List<Text> wrapAndTranslateStatsToTextList(MinecraftClient client,
            HashMap<String, FabricRPGItemStackStatInterface> stats) {
        return wrapAndTranslateStatsToTextList(client, stats, new ArrayList<Text>());
    }

    public static List<String> wrapAndTranslateStatsToStringList(MinecraftClient client,
            HashMap<String, FabricRPGItemStackStatInterface> stats) {
        List<String> wrappedAndTranslatedStatsStringList = new ArrayList<String>();
        stats.forEach((string,
                value) -> wrappedAndTranslatedStatsStringList.addAll(FormattingEngine.wrapStringToWidth(
                        new TranslatableText("custom_stat.fabric_rpg." + string + ".tooltip", value.getStatValue())
                                .asString(), // string
                        70, // wrapWidth
                        (c) -> (int) client.textRenderer.getCharWidth((char) c), // character width function
                        Locale.forLanguageTag(client.getLanguageManager().getLanguage().getCode()), // locale of
                                                                                                    // minecraft
                                                                                                    // client
                        FormattingEngine.COLOR_CODE.WHITE)));
        return wrappedAndTranslatedStatsStringList;
    }

    public static List<Text> createTooltipWithStatsForNonFabricRPGItem(List<Text> tooltipTextList, int wrapWidth,
            HashMap<String, FabricRPGItemStackStatInterface> stats, Item item) {
        return wrapAndTranslateStatsToTextList(MinecraftClient.getInstance(), stats, tooltipTextList);
    }

    public static List<Text> createTooltipWithStats(List<Text> tooltipTextList, int wrapWidth,
            HashMap<String, FabricRPGItemStackStatInterface> stats) {
        return wrapAndTranslateStatsToTextList(MinecraftClient.getInstance(), stats, tooltipTextList);
    }

    public static List<Text> createTooltipWithStats(String itemName, List<Text> tooltipTextList, int wrapWidth,
            HashMap<String, FabricRPGItemStackStatInterface> stats) {
        MinecraftClient client = MinecraftClient.getInstance();
        return wrapAndTranslateStatsToTextList(client, stats,
                wrapAndTranslateKeyToTextList("item.fabric_rpg." + itemName + ".tooltip", client, tooltipTextList));
    }

    public static List<Text> createTooltip(String itemName, List<Text> tooltipTextList, int wrapWidth) {
        return wrapAndTranslateKeyToTextList("item.fabric_rpg." + itemName + ".tooltip", MinecraftClient.getInstance(),
                tooltipTextList);
    }
}