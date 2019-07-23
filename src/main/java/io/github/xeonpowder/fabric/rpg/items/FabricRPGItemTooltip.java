package io.github.xeonpowder.fabric.rpg.items;

import java.util.List;
import java.util.Locale;

import io.github.TUSK__3.panI18n.FormattingEngine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class FabricRPGItemTooltip {

    public static void createTooltip(String itemName, List<Text> tooltipTextList, int wrapWidth) {
        MinecraftClient client = MinecraftClient.getInstance();
        List<String> wrappedLocalizedTextAsStringList = FormattingEngine.wrapStringToWidth(
                new TranslatableText("item.fabric_rpg." + itemName + ".tooltip").asString(), // string
                70, // wrapWidth
                (c) -> (int) client.textRenderer.getCharWidth((char) c), // character width function
                Locale.forLanguageTag(client.getLanguageManager().getLanguage().getCode()), // locale of
                                                                                            // minecraft
                                                                                            // client
                FormattingEngine.COLOR_CODE.WHITE); // default color code
        wrappedLocalizedTextAsStringList.forEach(string -> tooltipTextList.add(new LiteralText(string)));
    }
}