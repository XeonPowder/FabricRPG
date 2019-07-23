package io.github.xeonpowder.fabric.rpg;

import net.fabricmc.api.ModInitializer;

// import java.util.ArrayList;

// import io.github.xeonpowder.fabric.rpg.items.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.loader.ItemLoader;

public class FabricRPG implements ModInitializer {
	public static final String MODID = "fabric_rpg";
	// public static final SoulRune SOUL_RUNE = new SoulRune(new
	// Item.Settings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		// Register Items
		new ItemLoader("io.github.xeonpowder.fabric.rpg.items").getItems();

		// Font Renderer

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
