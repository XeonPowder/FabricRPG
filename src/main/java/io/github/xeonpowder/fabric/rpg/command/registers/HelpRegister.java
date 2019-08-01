package io.github.xeonpowder.fabric.rpg.command.registers;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.github.xeonpowder.fabric.rpg.command.EmptyRegister;
import io.github.xeonpowder.fabric.rpg.command.functions.SendMessageToPlayer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class HelpRegister implements EmptyRegister {
    @Override
    public LiteralCommandNode regsiterMain(CommandDispatcher<ServerCommandSource> dispatcher) {
        return dispatcher.register(CommandManager.literal("help").executes(
                ctx -> SendMessageToPlayer.fromServerCommandSource(ctx.getSource(), "command.fabric_rpg.help")));
    }

    public HelpRegister() {

    }

    @Override
    public String getRegisterName() {
        return "help";
    }

}