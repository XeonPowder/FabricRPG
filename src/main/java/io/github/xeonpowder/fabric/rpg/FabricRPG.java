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
	public static final String COMMAND_NAME = "frpg";
	public static final String COMMAND_SHORTNAME = "f";
	public static final String MODID = "fabric_rpg";
	// public static final SoulRune SOUL_RUNE = new SoulRune(new
	// Item.Settings().group(ItemGroup.MISC));
	public static final String CLIENT_TAG = "[Client] ";
	public static final String SERVER_TAG = "[Server] ";
	// public static final MinecraftClient CLIENT_INSTANCE =
	// MinecraftClient.getInstance();
	// public static final boolean isClient = CLIENT_INSTANCE.world.isClient;
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

	public void loadServer() {
		System.out.println(asClientOrServer("Fabric-RPG initializing!"));
		// Register Items
		new ItemLoader("io.github.xeonpowder.fabric.rpg.item.items");

		System.out.println(asClientOrServer("Loaded Fabric RPG Items"));

		// Register Stat Types
		new StatLoader("io.github.xeonpowder.fabric.rpg.stat.stats");

		System.out.println(asClientOrServer("Loaded Fabric RPG Stats"));
		System.out.println(asClientOrServer("Fabric-RPG initialized!"));
	}

	public void loadClient() {
		System.out.println(asClientOrServer("Fabric-RPG initializing!"));
		// Register Items
		new ItemLoader("io.github.xeonpowder.fabric.rpg.item.items");
		System.out.println(asClientOrServer("Loaded Fabric RPG Items"));
		// Register Stat Types
		new StatLoader("io.github.xeonpowder.fabric.rpg.stat.stats");
		System.out.println(asClientOrServer("Loaded Fabric RPG Stats"));
		System.out.println(asClientOrServer("Fabric-RPG initialized!"));
	}
	// private void regsiterItems(ArrayList<FabricRPGItem> items) {
	// items.forEach((item) -> Registry.register(Registry.ITEM, new
	// Identifier(MODID, item.getItemName()), item));
	// }
}
