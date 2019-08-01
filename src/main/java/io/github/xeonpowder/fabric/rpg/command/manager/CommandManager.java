package io.github.xeonpowder.fabric.rpg.command.manager;

import java.util.HashMap;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import org.apache.logging.log4j.util.TriConsumer;

import io.github.xeonpowder.fabric.rpg.command.commands.HelpCommand;
import io.github.xeonpowder.fabric.rpg.command.loader.CommandLoader;
import io.github.xeonpowder.fabric.rpg.command.registers.HelpRegister;
import net.minecraft.server.command.ServerCommandSource;

public class CommandManager {
    public HashMap<String, TriConsumer<CommandDispatcher<ServerCommandSource>, String, HashMap<String, LiteralCommandNode>>> CommandMap;

    public CommandManager() {
        this.CommandMap = new HashMap<>();
        genCommandLoaders();
    }

    public HashMap<String, TriConsumer<CommandDispatcher<ServerCommandSource>, String, HashMap<String, LiteralCommandNode>>> getCommandMap() {
        return this.CommandMap;
    }

    private void genCommandLoaders() {
        new CommandLoader<HelpRegister, HelpCommand>(HelpRegister.class, HelpCommand.class, this);
    }
}