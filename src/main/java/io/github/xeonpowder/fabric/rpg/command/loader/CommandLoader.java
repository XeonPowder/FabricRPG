package io.github.xeonpowder.fabric.rpg.command.loader;

import java.lang.reflect.InvocationTargetException;
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
        try {
            this.registerInstance = registererClass.getDeclaredConstructor().newInstance();
            this.commandInstance = commandClass.getDeclaredConstructor(registererClass).newInstance(registerInstance);

            this.commandName = this.getInstance().getCommandName();
            System.out.println(cm);
            System.out.println(cm.getCommandMap());
            cm.getCommandMap().put(this.commandName, (dispatcher, command,
                    commandNodes) -> bind(this.registerInstance::regsiterMain, dispatcher, command, commandNodes));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            commandInstance = null;
        }
    }

    private Supplier<Boolean> bind(Function<CommandDispatcher<ServerCommandSource>, LiteralCommandNode> fn,
            CommandDispatcher<ServerCommandSource> dispatcher, String command,
            HashMap<String, LiteralCommandNode> commandNodes) {
        return () -> {
            LiteralCommandNode node = fn.apply(dispatcher);
            commandNodes.put(command, node);
            dispatcher.register(CommandManager.literal(FabricRPG.COMMAND_NAME).then(CommandManager.literal(command))
                    .redirect(node));
            dispatcher.register(CommandManager.literal(FabricRPG.COMMAND_SHORTNAME)
                    .then(CommandManager.literal(command)).redirect(node));
            return true;
        };
    }

    public S getInstance() {
        return commandInstance;
    }
}