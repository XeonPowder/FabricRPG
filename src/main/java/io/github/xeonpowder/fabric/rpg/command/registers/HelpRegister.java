package io.github.xeonpowder.fabric.rpg.command.registers;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.command.EmptyRegister;
import io.github.xeonpowder.fabric.rpg.command.functions.SendMessageToPlayer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class HelpRegister implements EmptyRegister {

    @Override
    public LiteralCommandNode regsiterMain(CommandDispatcher<ServerCommandSource> dispatcher, String commandName) {
        System.out.println("help register registerMain");
        return dispatcher.register(CommandManager.literal(commandName)
                .then(CommandManager.literal(this.getRegisterName()).executes(ctx -> SendMessageToPlayer
                        .fromServerCommandSource(ctx.getSource(), this.getTranslationKey()))));
    }

    public HelpRegister() {

    }

    @Override
    public String getRegisterName() {
        return "help";
    }

    @Override
    public String getTranslationKey() {
        return "command.fabric_rpg.help";
    }

}