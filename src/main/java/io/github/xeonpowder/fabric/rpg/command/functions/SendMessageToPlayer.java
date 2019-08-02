package io.github.xeonpowder.fabric.rpg.command.functions;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import io.github.TUSK__3.panI18n.FormattingEngine;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class SendMessageToPlayer {
    public static int fromServerCommandSource(ServerCommandSource source, String translatableTextKey)
            throws CommandSyntaxException {
        System.out.println("sending message to player");
        source.getPlayer().sendMessage(new LiteralText(
                FormattingEngine.replaceColorCodeEnumInString(new TranslatableText(translatableTextKey).asString())));
        return Command.SINGLE_SUCCESS;
    }
}