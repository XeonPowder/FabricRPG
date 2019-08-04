package io.github.xeonpowder.fabric.rpg;

import io.github.xeonpowder.fabric.rpg.block.loader.PlantBlockLoader;
import io.github.xeonpowder.fabric.rpg.command.FabricRPGBaseCommand;
import io.github.xeonpowder.fabric.rpg.command.manager.CommandManager;
import io.github.xeonpowder.fabric.rpg.item.loader.ItemLoader;
import io.github.xeonpowder.fabric.rpg.stat.loader.StatLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.CommandRegistry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

// import java.util.ArrayList;

public class FabricRPG implements ModInitializer {
	public static final String MODID = "fabric_rpg";
	public static final String CLIENT_TAG = "[Client] ";
	public static final String SERVER_TAG = "[Server] ";
	public static final String[] COMMAND_NAMES = new String[] { "frpg", "f" };
	public static CommandManager COMMAND_MANAGER = new CommandManager();
	private static final ItemLoader ITEMS = new ItemLoader("io.github.xeonpowder.fabric.rpg.item.items");
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
        new Identifier(MODID, "items"))
        .icon(() -> new ItemStack(ITEMS.getItems().get(0)))
        .build();
	@Override
	public void onInitialize() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> FabricRPGBaseCommand.register(dispatcher));

		System.out.println(("Fabric-RPG initializing!"));

		// System.out.println(asClientOrServer("Loaded Fabric RPG Items"));
		// Register Plant Blocks
		new PlantBlockLoader("io.github.xeonpowder.fabric.rpg.block.blocks.plant");

		System.out.println(("Loaded Fabric RPG Plant Blocks"));

		// Register Stat Types
		new StatLoader("io.github.xeonpowder.fabric.rpg.stat.stats");
		System.out.println("Loaded Fabric RPG Stats");
		System.out.println("Fabric-RPG initialized!");
	}
}
