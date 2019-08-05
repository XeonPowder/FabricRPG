package io.github.xeonpowder.fabric.rpg;

import io.github.xeonpowder.fabric.rpg.block.loader.PlantBlockLoader;
import io.github.xeonpowder.fabric.rpg.command.FabricRPGBaseCommand;
import io.github.xeonpowder.fabric.rpg.command.manager.CommandManager;
import io.github.xeonpowder.fabric.rpg.item.loader.ItemLoader;
import io.github.xeonpowder.fabric.rpg.portalnetwork.LevelPropertiesPortalNetwork;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PlayerPortalNetwork;
import io.github.xeonpowder.fabric.rpg.portalnetwork.WorldPortalNetwork;
import io.github.xeonpowder.fabric.rpg.server.packet.ServerPacketConsumerRegistrator;
import io.github.xeonpowder.fabric.rpg.stat.loader.StatLoader;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.event.LevelComponentCallback;
import nerdhub.cardinal.components.api.event.WorldComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

// import java.util.ArrayList;

public class FabricRPG implements ModInitializer {
	public static final String MODID = "fabric_rpg";
	public static final String CLIENT_TAG = "[Client] ";
	public static final String SERVER_TAG = "[Server] ";
	public static final String[] COMMAND_NAMES = new String[] { "frpg", "f" };
	public static final CommandManager COMMAND_MANAGER = new CommandManager();
	private static final ItemLoader ITEM_LOADER = new ItemLoader("io.github.xeonpowder.fabric.rpg.item.items");
	private static final PlantBlockLoader PLANT_BLOCK_LOADER = new PlantBlockLoader(
			"io.github.xeonpowder.fabric.rpg.block.blocks.plant");
	private static final StatLoader STAT_LOADER = new StatLoader("io.github.xeonpowder.fabric.rpg.stat.stats");
	private static final ServerPacketConsumerRegistrator SERVER_PACKET_REGISTRATOR = new ServerPacketConsumerRegistrator(
			"io.github.xeonpowder.fabric.rpg.server.packet.consumer");

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "items"))
			.icon(() -> new ItemStack(ITEM_LOADER.getItems().get(0))).build();

	@Override
	public void onInitialize() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> FabricRPGBaseCommand.register(dispatcher));

		System.out.println("Fabric-RPG initializing!");
		EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> components
				.put(PlayerPortalNetwork.PLAYER_ENTITY_PORTAL_NETWORK, new PlayerPortalNetwork(player)));

		WorldComponentCallback.EVENT.register((world, components) -> components
				.put(WorldPortalNetwork.WORLD_PORTAL_NETWORK, new WorldPortalNetwork(world)));

		LevelComponentCallback.EVENT.register((level, components) -> components.put(
				LevelPropertiesPortalNetwork.LEVEL_PROPERTIES_PORTAL_NETWORK, new LevelPropertiesPortalNetwork(level)));
		System.out.println("Registered Fabric RPG Portal Networks!");
		System.out.println("Fabric-RPG initialized!");

	}
}
