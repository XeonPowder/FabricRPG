package io.github.xeonpowder.fabric.rpg;

import io.github.xeonpowder.fabric.rpg.block.FabricRPGBlockItem;
import io.github.xeonpowder.fabric.rpg.block.loader.PlantBlockLoader;
import io.github.xeonpowder.fabric.rpg.command.FabricRPGBaseCommand;
import io.github.xeonpowder.fabric.rpg.command.manager.CommandManager;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltip;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltipCallback;
import io.github.xeonpowder.fabric.rpg.item.loader.ItemLoader;
import io.github.xeonpowder.fabric.rpg.portalnetwork.LevelPropertiesPortalNetwork;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PlayerPortalNetwork;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PortalNetworkComponent;
import io.github.xeonpowder.fabric.rpg.portalnetwork.WorldPortalNetwork;
import io.github.xeonpowder.fabric.rpg.server.packet.ServerPacketConsumerRegistrator;
import io.github.xeonpowder.fabric.rpg.stat.FabricRPGItemStackStats;
import io.github.xeonpowder.fabric.rpg.stat.loader.StatLoader;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.event.LevelComponentCallback;
import nerdhub.cardinal.components.api.event.WorldComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

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
	public static final ComponentType<PortalNetworkComponent> PortalNetworkComponent = ComponentRegistry.INSTANCE
			.registerIfAbsent(new Identifier(FabricRPG.MODID, "portal_network_component"),
					PortalNetworkComponent.class);
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "items"))
			.icon(() -> new ItemStack(ITEM_LOADER.getItems().get(0))).build();

	@Override
	public void onInitialize() {

		FabricRPGItemTooltipCallback.EVENT.register((stack, player, tooltipContext, components) -> {
			if (stack != null && tooltipContext != null && components != null) {
				if (player.world.isAreaLoaded(player.getBlockPos(), player.getBlockPos())) {
					boolean isFabricRPGItem = (stack.getItem() instanceof FabricRPGItem);
					boolean isFabricRPGBlockItem = (stack.getItem() instanceof FabricRPGBlockItem);
					if (stack.getMaxDamage() > 0) {
						components.addAll(
								FabricRPGItemTooltip.addDurabilityOfItemStackToTooltip(new ArrayList<Text>(), stack));
					}
					CompoundTag stackTag = stack.hasTag() ? stack.getTag() : new CompoundTag();
					if (isFabricRPGItem || isFabricRPGBlockItem) {
						components.addAll(FabricRPGItemTooltip.createTooltipWithStatsForFabricRPGItem(
								isFabricRPGItem ? "item" : "block",
								((FabricRPGItem) stack.getItem()).getTranslationKey(), new ArrayList<Text>(),
								FabricRPGItemTooltip.WRAP_WIDTH,
								FabricRPGItemStackStats.createStatsMapFromCompoundTag(stackTag)));

					} else {
						components.addAll(FabricRPGItemTooltip.createStatsTooltipForNonFabricRPGItem(
								new ArrayList<Text>(), FabricRPGItemTooltip.WRAP_WIDTH,
								FabricRPGItemStackStats.createStatsMapFromCompoundTag(stackTag)));
					}
				}

			}

		});
		CommandRegistry.INSTANCE.register(false, dispatcher -> FabricRPGBaseCommand.register(dispatcher));

		System.out.println("Fabric-RPG initializing!");
		EntityComponentCallback.event(PlayerEntity.class).register(
				(player, components) -> components.put(PortalNetworkComponent, new PlayerPortalNetwork(player)));

		WorldComponentCallback.EVENT.register((world, components) -> {
			components.put(PortalNetworkComponent, new WorldPortalNetwork(world));
		});

		LevelComponentCallback.EVENT.register(
				(level, components) -> components.put(PortalNetworkComponent, new LevelPropertiesPortalNetwork(level)));
		System.out.println("Registered Fabric RPG Portal Networks!");
		System.out.println("Fabric-RPG initialized!");

	}
}
