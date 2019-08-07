package io.github.xeonpowder.fabric.rpg;

import io.github.xeonpowder.fabric.rpg.block.blocks.plant.PortalPlantBlock;
import io.github.xeonpowder.fabric.rpg.command.FabricRPGBaseCommand;
import io.github.xeonpowder.fabric.rpg.command.manager.CommandManager;
import io.github.xeonpowder.fabric.rpg.item.items.Blood;
import io.github.xeonpowder.fabric.rpg.item.items.BloodPotion;
import io.github.xeonpowder.fabric.rpg.item.items.DnaPotion;
import io.github.xeonpowder.fabric.rpg.item.items.Lightning;
import io.github.xeonpowder.fabric.rpg.item.items.LightningSword;
import io.github.xeonpowder.fabric.rpg.item.items.Plasma;
import io.github.xeonpowder.fabric.rpg.item.items.PlasmaPotion;
import io.github.xeonpowder.fabric.rpg.item.items.PortalPotion;
import io.github.xeonpowder.fabric.rpg.item.items.Soul;
import io.github.xeonpowder.fabric.rpg.item.items.SoulRune;
import io.github.xeonpowder.fabric.rpg.item.items.SoulStone;
import io.github.xeonpowder.fabric.rpg.item.items.StopStick;
import io.github.xeonpowder.fabric.rpg.portalnetwork.LevelPropertiesPortalNetwork;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PlayerPortalNetwork;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PortalNetworkComponent;
import io.github.xeonpowder.fabric.rpg.portalnetwork.WorldPortalNetwork;
import io.github.xeonpowder.fabric.rpg.server.packet.consumer.PlayerPortalNetworkConsumer;
import io.github.xeonpowder.fabric.rpg.server.packet.consumer.StatPacketConsumer;
import io.github.xeonpowder.fabric.rpg.stat.stats.FabricRPGAttackSpeedStat;
import io.github.xeonpowder.fabric.rpg.stat.stats.FabricRPGBloodStat;
import io.github.xeonpowder.fabric.rpg.stat.stats.FabricRPGLifeStealStat;
import io.github.xeonpowder.fabric.rpg.stat.stats.FabricRPGSoulStat;
import io.github.xeonpowder.fabric.rpg.timber.PlayerTimber;
import io.github.xeonpowder.fabric.rpg.timber.TimberComponent;
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
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

// import java.util.ArrayList;

public class FabricRPG implements ModInitializer {
	public static final String MODID = "fabric_rpg";
	public static final String CLIENT_TAG = "[Client] ";
	public static final String SERVER_TAG = "[Server] ";
	public static final String[] COMMAND_NAMES = new String[] { "frpg", "f" };
	public static final CommandManager COMMAND_MANAGER = new CommandManager();
	public static final ComponentType<PortalNetworkComponent> PortalNetworkComponent = ComponentRegistry.INSTANCE
			.registerIfAbsent(new Identifier(FabricRPG.MODID, "portal_network_component"),
					PortalNetworkComponent.class);

	public static final ComponentType<TimberComponent> TIMBER = ComponentRegistry.INSTANCE
			.registerIfAbsent(new Identifier(FabricRPG.MODID, "timber"), TimberComponent.class);

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "items"))
			.icon(() -> new ItemStack(Registry.ITEM.get(new Identifier(MODID, "lightning")))).build();

	@Override
	public void onInitialize() {

		CommandRegistry.INSTANCE.register(false, dispatcher -> FabricRPGBaseCommand.register(dispatcher));

		System.out.println("Fabric-RPG initializing!");
		EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> {
			components.put(TIMBER, new PlayerTimber(player));
			components.put(PortalNetworkComponent, new PlayerPortalNetwork(player));
		});

		WorldComponentCallback.EVENT.register((world, components) -> {
			components.put(PortalNetworkComponent, new WorldPortalNetwork(world));
		});

		this.registerAll();
		LevelComponentCallback.EVENT.register(
				(level, components) -> components.put(PortalNetworkComponent, new LevelPropertiesPortalNetwork(level)));
		System.out.println("Registered Fabric RPG Portal Networks!");
		System.out.println("Fabric-RPG initialized!");

	}

	public void registerAll() {
		this.registerItems();
		this.registerBlocks();
		this.registerPackets();
		this.registerStats();
	}

	public void registerItems() {
		new Blood();
		new BloodPotion();
		new DnaPotion();
		new Lightning();
		new LightningSword();
		new Plasma();
		new PlasmaPotion();
		new PortalPotion();
		new Soul();
		new SoulRune();
		new SoulStone();
		new StopStick();
	}

	public void registerBlocks() {
		new PortalPlantBlock();
	}

	public void registerPackets() {
		new PlayerPortalNetworkConsumer();
		new StatPacketConsumer();
	}

	public void registerStats() {
		new FabricRPGAttackSpeedStat().registerStatInStatTypeHashMap();
		new FabricRPGBloodStat().registerStatInStatTypeHashMap();
		new FabricRPGLifeStealStat().registerStatInStatTypeHashMap();
		new FabricRPGSoulStat().registerStatInStatTypeHashMap();
	}
}
