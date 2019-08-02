package io.github.xeonpowder.fabric.rpg;

import io.github.xeonpowder.fabric.rpg.command.FabricRPGBaseCommand;
import io.github.xeonpowder.fabric.rpg.command.manager.CommandManager;
import io.github.xeonpowder.fabric.rpg.item.loader.ItemLoader;
import io.github.xeonpowder.fabric.rpg.stat.loader.StatLoader;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CommandRegistry;

import net.minecraft.client.MinecraftClient;

// import java.util.ArrayList;

public class FabricRPG implements ModInitializer {
	public static final String MODID = "fabric_rpg";
	public static final String CLIENT_TAG = "[Client] ";
	public static final String SERVER_TAG = "[Server] ";
	public static final String[] COMMAND_NAMES = new String[] { "frpg", "f" };
	public static CommandManager COMMAND_MANAGER = new CommandManager();

	@Override
	public void onInitialize() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> FabricRPGBaseCommand.register(dispatcher));

		System.out.println(asClientOrServer("Fabric-RPG initializing!"));
		// Register Items
		new ItemLoader("io.github.xeonpowder.fabric.rpg.item.items");

		System.out.println(asClientOrServer("Loaded Fabric RPG Items"));

		// Register Stat Types
		new StatLoader("io.github.xeonpowder.fabric.rpg.stat.stats");

		System.out.println(asClientOrServer("Loaded Fabric RPG Stats"));
		System.out.println(asClientOrServer("Fabric-RPG initialized!"));
	}

	public static String asClientOrServer(String string) {
		if (MinecraftClient.getInstance() != null && MinecraftClient.getInstance().world != null) {
			return MinecraftClient.getInstance().world.isClient ? CLIENT_TAG + string : SERVER_TAG + string;
		}
		return string;

	}
}
