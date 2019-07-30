package io.github.xeonpowder.fabric.rpg;

import java.util.HashMap;

import io.github.xeonpowder.fabric.rpg.entity.FabricRPGLivingEntityDB;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.loader.ItemLoader;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStackDB;
import io.github.xeonpowder.fabric.rpg.stat.loader.StatLoader;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;

// import java.util.ArrayList;

public class FabricRPG implements ModInitializer {
	public static final String MODID = "fabric_rpg";
	// public static final SoulRune SOUL_RUNE = new SoulRune(new
	// Item.Settings().group(ItemGroup.MISC));
	public static HashMap<Item, FabricRPGItemStackDB> ItemStackDB = new HashMap<>();
	public static HashMap<String, FabricRPGLivingEntityDB> LivingEntityDB = new HashMap<>();

	@Override
	public void onInitialize() {
		// Register Items
		new ItemLoader("io.github.xeonpowder.fabric.rpg.item.items");
		System.out.println("Loaded Fabric RPG Items");
		// Register Stat Types
		new StatLoader("io.github.xeonpowder.fabric.rpg.stat.stats");
		System.out.println("Loaded Fabric RPG Stats");

		// regsiterItems(FabricRPGItems);
		// Registry.register(Registry.ITEM, new Identifier("fabric_rpg", "soul_rune"),
		// SOUL_RUNE);
		System.out.println("Hello from Fabric-RPG!");
	}

	// private void regsiterItems(ArrayList<FabricRPGItem> items) {
	// items.forEach((item) -> Registry.register(Registry.ITEM, new
	// Identifier(MODID, item.getItemName()), item));
	// }
}
