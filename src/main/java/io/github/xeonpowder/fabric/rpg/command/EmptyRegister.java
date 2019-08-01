package io.github.xeonpowder.fabric.rpg.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.minecraft.server.command.ServerCommandSource;

public interface EmptyRegister {
    public String getRegisterName();

    public LiteralCommandNode regsiterMain(CommandDispatcher<ServerCommandSource> dispatcher);
}