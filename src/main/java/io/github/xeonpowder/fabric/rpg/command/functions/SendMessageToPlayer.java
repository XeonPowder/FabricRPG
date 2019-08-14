package io.github.xeonpowder.fabric.rpg.command.functions;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class SendMessageToPlayer {
    public static int fromServerCommandSource(ServerCommandSource source, String message)
            throws CommandSyntaxException {
        source.getPlayer().sendMessage(new LiteralText(message));
        return Command.SINGLE_SUCCESS;
    }
}