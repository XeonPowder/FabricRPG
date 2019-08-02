package io.github.xeonpowder.fabric.rpg.command.loader;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.command.EmptyCommand;
import io.github.xeonpowder.fabric.rpg.command.EmptyRegister;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class CommandLoader<T extends EmptyRegister, S extends EmptyCommand<T>> {

    private S commandInstance;
    private T registerInstance;
    private String commandName;

    public CommandLoader(Class<T> registererClass, Class<S> commandClass,
            io.github.xeonpowder.fabric.rpg.command.manager.CommandManager cm) {
        System.out.println("command loader...");
        try {
            this.registerInstance = registererClass.getDeclaredConstructor().newInstance();
            this.commandInstance = commandClass.getDeclaredConstructor(registererClass).newInstance(registerInstance);
            this.commandName = this.getInstance().getCommandName();
            cm.getCommandMap().put(this.commandName, (dispatcher, command) -> {
                System.out.println(this.commandName + " was registered!");
                Arrays.asList(FabricRPG.COMMAND_NAMES).forEach(frpgCommandName -> {
                    LiteralCommandNode node = this.registerInstance.regsiterMain(dispatcher, frpgCommandName);
                    dispatcher.register(CommandManager.literal(frpgCommandName)
                            .then(CommandManager.literal(command).redirect(node)));
                });
            });
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    public S getInstance() {
        return commandInstance;
    }
}