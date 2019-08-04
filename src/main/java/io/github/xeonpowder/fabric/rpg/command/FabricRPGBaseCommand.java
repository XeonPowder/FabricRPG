package io.github.xeonpowder.fabric.rpg.command;

import java.util.Arrays;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.command.functions.SendMessageToPlayer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class FabricRPGBaseCommand {
    public static final List<String> Commands = Arrays.asList(new String[] { "help" });

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        Arrays.asList(FabricRPG.COMMAND_NAMES).forEach(frpgCommandName -> {
            dispatcher.register(CommandManager.literal(frpgCommandName).executes(ctx -> {
                return SendMessageToPlayer.fromServerCommandSource(ctx.getSource(), "command.fabric_rpg.main");
            }));
        });
        Commands.forEach(command -> {
            // Class<? extends EmptyCommand<?>> commandClass =
            // FabricRPG.CommandClasses.get(command).getDeclaredConstructor().newInstance();
            // Class<? extends EmptyCommand<?>> commandInstance = commandClass;
            System.out.println(command);
            FabricRPG.COMMAND_MANAGER.getCommandMap().get(command).accept(dispatcher, command);
        });
    }
}