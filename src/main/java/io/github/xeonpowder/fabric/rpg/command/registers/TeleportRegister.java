package io.github.xeonpowder.fabric.rpg.command.registers;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.github.xeonpowder.fabric.rpg.command.functions.SendMessageToPlayer;
import io.github.xeonpowder.fabric.rpg.command.functions.TeleportViaPortalNetwork;
import net.minecraft.command.arguments.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.command.EmptyRegister;

/**
 * TeleportRegister
 */
public class TeleportRegister implements EmptyRegister {

    @Override
    public LiteralCommandNode<ServerCommandSource> regsiterMain(CommandDispatcher<ServerCommandSource> dispatcher,
            String commandName) {
        String commandHelpString = FormattingEngine
                .replaceColorCodeEnumInString(new TranslatableText(this.getTranslationKey()).asString());
        // System.out.println("help register registerMain");
        return dispatcher.register(CommandManager.literal(commandName)
                .then(CommandManager.literal(this.getRegisterName())
                        .then(CommandManager.argument("target", EntityArgumentType.player())
                                .executes(ctx -> TeleportViaPortalNetwork.player2player(ctx.getSource().getPlayer(),
                                        EntityArgumentType.getPlayer(ctx, "target")))))
                .executes(ctx -> SendMessageToPlayer.fromServerCommandSource(ctx.getSource(), commandHelpString)));
    }

    public TeleportRegister() {

    }

    @Override
    public String getRegisterName() {
        return "teleport";
    }

    @Override
    public String getTranslationKey() {
        return "command.fabric_rpg.teleport";
    }

}