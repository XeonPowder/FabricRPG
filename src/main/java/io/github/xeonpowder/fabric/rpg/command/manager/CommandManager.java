package io.github.xeonpowder.fabric.rpg.command.manager;

import java.util.HashMap;
import java.util.function.BiConsumer;

import com.mojang.brigadier.CommandDispatcher;

import io.github.xeonpowder.fabric.rpg.command.commands.HelpCommand;
import io.github.xeonpowder.fabric.rpg.command.commands.TeleportCommand;
import io.github.xeonpowder.fabric.rpg.command.loader.CommandLoader;
import io.github.xeonpowder.fabric.rpg.command.registers.HelpRegister;
import io.github.xeonpowder.fabric.rpg.command.registers.TeleportRegister;
import net.minecraft.server.command.ServerCommandSource;

public class CommandManager {
    public HashMap<String, BiConsumer<CommandDispatcher<ServerCommandSource>, String>> CommandMap;

    public CommandManager() {
        this.CommandMap = new HashMap<>();
        // System.out.println("command manager initialized");
        genCommandLoaders();
    }

    public HashMap<String, BiConsumer<CommandDispatcher<ServerCommandSource>, String>> getCommandMap() {
        return this.CommandMap;
    }

    private void genCommandLoaders() {
        // System.out.println("generating command loaders");
        new CommandLoader<HelpRegister, HelpCommand>(HelpRegister.class, HelpCommand.class, this);
        new CommandLoader<TeleportRegister, TeleportCommand>(TeleportRegister.class, TeleportCommand.class, this);
    }
}