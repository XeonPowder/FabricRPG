package io.github.xeonpowder.fabric.rpg.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;

import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.command.functions.SendMessageToPlayer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

public class FabricRPGBaseCommand {
    public static final List<ArrayList<String>> Commands = new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList("help")), new ArrayList<String>(Arrays.asList("teleport", "tp")),
            new ArrayList<String>(Arrays.asList("currency"))));

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        Arrays.asList(FabricRPG.COMMAND_NAMES).forEach(frpgCommandName -> {
            dispatcher.register(CommandManager.literal(frpgCommandName).executes(ctx -> {
                return SendMessageToPlayer.fromServerCommandSource(ctx.getSource(), FormattingEngine
                        .replaceColorCodeEnumInString(new TranslatableText("command.fabric_rpg.main").asString()));
            }));
        });
        Commands.forEach(command -> {
            command.forEach(alias -> {
                FabricRPG.COMMAND_MANAGER.getCommandMap().get(command.get(0)).accept(dispatcher, alias);
            });
        });
    }
}