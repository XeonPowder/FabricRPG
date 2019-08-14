package io.github.xeonpowder.fabric.rpg.command.registers;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.command.EmptyRegister;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class HelpRegister implements EmptyRegister {

    @Override
    public LiteralCommandNode<ServerCommandSource> regsiterMain(CommandDispatcher<ServerCommandSource> dispatcher,
            String commandName) {
        // System.out.println("help register registerMain");
        return dispatcher.register(CommandManager.literal(commandName)
                .then(CommandManager.literal(this.getRegisterName()).executes(ctx -> {
                    ctx.getSource().getPlayer().sendMessage(new LiteralText(FormattingEngine
                            .replaceColorCodeEnumInString(new TranslatableText(this.getTranslationKey()).asString())));
                    return Command.SINGLE_SUCCESS;
                })));
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