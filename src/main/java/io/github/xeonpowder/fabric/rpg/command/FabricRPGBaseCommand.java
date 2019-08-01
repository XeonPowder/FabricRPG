package io.github.xeonpowder.fabric.rpg.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FabricRPGBaseCommand {
    public static final List<String> Commands = Arrays.asList(new String[] { "help" });
    public static HashMap<String, LiteralCommandNode> commandNodes = new HashMap<>();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        Commands.forEach(command -> {
            // Class<? extends EmptyCommand<?>> commandClass =
            // FabricRPG.CommandClasses.get(command).getDeclaredConstructor().newInstance();
            // Class<? extends EmptyCommand<?>> commandInstance = commandClass;
            FabricRPG.COMMAND_MANAGER.CommandMap.get(command).accept(dispatcher, command, commandNodes);
        });
    }
}