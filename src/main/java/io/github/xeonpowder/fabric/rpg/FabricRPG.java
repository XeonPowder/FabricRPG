package io.github.xeonpowder.fabric.rpg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.github.xeonpowder.fabric.rpg.block.blocks.head.PortalHalo;
import io.github.xeonpowder.fabric.rpg.block.blocks.head.PortalHaloBlockEntity;
import io.github.xeonpowder.fabric.rpg.block.blocks.head.PortalHaloBlockEntityRenderer;
import io.github.xeonpowder.fabric.rpg.block.blocks.plant.PortalPlantBlock;
import io.github.xeonpowder.fabric.rpg.block.blocks.plant.PortalPlantBlockEntity;
import io.github.xeonpowder.fabric.rpg.block.blocks.plant.PortalPlantBlockEntityRenderer;
import io.github.xeonpowder.fabric.rpg.command.FabricRPGBaseCommand;
import io.github.xeonpowder.fabric.rpg.command.manager.CommandManager;
import io.github.xeonpowder.fabric.rpg.entity.banana.Banana;
import io.github.xeonpowder.fabric.rpg.entity.banana.BigBanana;
import io.github.xeonpowder.fabric.rpg.entity.portalguardian.FabricRPGPortalGuardian;
import io.github.xeonpowder.fabric.rpg.item.items.Blood;
import io.github.xeonpowder.fabric.rpg.item.items.BloodPotion;
import io.github.xeonpowder.fabric.rpg.item.items.DnaPotion;
import io.github.xeonpowder.fabric.rpg.item.items.ExperiencePotion;
import io.github.xeonpowder.fabric.rpg.item.items.Lightning;
import io.github.xeonpowder.fabric.rpg.item.items.LightningSword;
import io.github.xeonpowder.fabric.rpg.item.items.Plasma;
import io.github.xeonpowder.fabric.rpg.item.items.PlasmaPotion;
import io.github.xeonpowder.fabric.rpg.item.items.PortalPotion;
import io.github.xeonpowder.fabric.rpg.item.items.ProfessionsTomb;
import io.github.xeonpowder.fabric.rpg.item.items.Soul;
import io.github.xeonpowder.fabric.rpg.item.items.SoulRune;
import io.github.xeonpowder.fabric.rpg.item.items.SoulStone;
import io.github.xeonpowder.fabric.rpg.item.items.StopStick;
import io.github.xeonpowder.fabric.rpg.portalnetwork.LevelPropertiesPortalNetwork;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PlayerPortalNetwork;
import io.github.xeonpowder.fabric.rpg.portalnetwork.PortalNetworkComponent;
import io.github.xeonpowder.fabric.rpg.portalnetwork.WorldPortalNetwork;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGPlayerProfessions;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGPlayerProfessionsComponent;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession.Profession;
import io.github.xeonpowder.fabric.rpg.profession.professions.mining.Mining;
import io.github.xeonpowder.fabric.rpg.profession.professions.mining.Mining.MiningAction;
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
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.GrassFeature;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.RandomRandomFeatureConfig;

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
	public static final ComponentType<TimberComponent> PlayerTimberComponent = ComponentRegistry.INSTANCE
			.registerIfAbsent(new Identifier(FabricRPG.MODID, "timber_component"), TimberComponent.class);
	public static final ComponentType<FabricRPGPlayerProfessionsComponent> PlayerProfessionsComponent = ComponentRegistry.INSTANCE
			.registerIfAbsent(new Identifier(FabricRPG.MODID, "player_professions_component"),
					FabricRPGPlayerProfessionsComponent.class);

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "items"))
			.icon(() -> new ItemStack(Registry.ITEM.get(new Identifier(MODID, "lightning")))).build();

	public static BlockEntityType<PortalHaloBlockEntity> PORTAL_HALO_BLOCK_ENTITY;
	public static HashMap<String, BlockEntityType<?>> BLOCK_ENTITIES;
	public static HashMap<Profession.ID, FabricRPGProfession> DEFAULT_PROFESSION_LIST;
	public static HashMap<Profession.ID, HashMap<String, FabricRPGProfession>> PROFESSION_LIST;
	public static HashMap<String, EntityType<?>> ENTITIES;
	static {
		ENTITIES = new HashMap<>();
		BLOCK_ENTITIES = new HashMap<>();
		DEFAULT_PROFESSION_LIST = new HashMap<>();
		PROFESSION_LIST = new HashMap<>();
		Arrays.asList(Profession.ID.values()).forEach(id -> {
			PROFESSION_LIST.put(id, new HashMap<>());
		});
	}

	@Override
	public void onInitialize() {

		CommandRegistry.INSTANCE.register(false, dispatcher -> FabricRPGBaseCommand.register(dispatcher));

		System.out.println("Fabric-RPG initializing!");
		EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> {
			components.put(PlayerTimberComponent, new PlayerTimber(player));
			components.put(PortalNetworkComponent, new PlayerPortalNetwork(player));
			components.put(PlayerProfessionsComponent, new FabricRPGPlayerProfessions(player));
		});

		WorldComponentCallback.EVENT.register((world, components) -> {
			components.put(PortalNetworkComponent, new WorldPortalNetwork(world));
		});
		LevelComponentCallback.EVENT.register(
				(level, components) -> components.put(PortalNetworkComponent, new LevelPropertiesPortalNetwork(level)));
		this.registerAll();
		System.out.println("Registered Fabric RPG Portal Networks!");
		System.out.println("Fabric-RPG initialized!");
		// generate portal plants on the surface
		GrassFeature PortalPlantFeature = (GrassFeature) Registry.register(Registry.FEATURE,
				(String) "frpg_portal_plant", new GrassFeature(GrassFeatureConfig::deserialize));
		Registry.BIOME.forEach(biome -> {
			biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(
					Feature.RANDOM_RANDOM_SELECTOR,
					new RandomRandomFeatureConfig(new Feature[] { PortalPlantFeature },
							new FeatureConfig[] { new GrassFeatureConfig(Registry.BLOCK
									.get(new Identifier(FabricRPG.MODID, "portal_plant_block")).getDefaultState()) },
							0),
					Decorator.COUNT_HEIGHTMAP_32, new CountDecoratorConfig(1)));
		});

	}

	public void registerAll() {
		System.out.println("Registering Fabric RPG");
		this.registerEntities();
		this.registerProfessions();
		this.registerItems();
		this.registerBlocks();
		this.registerBlockEntities();
		this.registerBlockEntityRenderers();
		this.registerPackets();
		this.registerStats();
		System.out.println("COMPLETED --- Registering Fabric RPG");
	}

	public void registerEntities() {
		System.out.println("Registering Fabric RPG --- Entities");
		ENTITIES.put("portal_guardian",
				Registry.register(Registry.ENTITY_TYPE, new Identifier(FabricRPG.MODID, "portal_guardian"),
						FabricEntityTypeBuilder.create(EntityCategory.AMBIENT, FabricRPGPortalGuardian::new)
								.size(EntityDimensions.fixed(1, 2)).build()));
		ENTITIES.put("banana",
				Registry.register(Registry.ENTITY_TYPE, new Identifier(FabricRPG.MODID, "banana"),
						FabricEntityTypeBuilder.create(EntityCategory.AMBIENT, Banana::new)
								.size(EntityDimensions.fixed(1, 2)).build()));
		ENTITIES.put("big_banana",
				Registry.register(Registry.ENTITY_TYPE, new Identifier(FabricRPG.MODID, "big_banana"),
						FabricEntityTypeBuilder.create(EntityCategory.AMBIENT, BigBanana::new)
								.size(EntityDimensions.fixed(1, 2)).build()));
		System.out.println("COMPLETED --- Registering Fabric RPG --- Entities");
	}

	public void registerProfessions() {
		System.out.println("Registering Fabric RPG --- Professions");
		FabricRPGPlayerProfessions.registerDefaultProfessionGlobally(Profession.ID.MINING,
				new Mining("professions.mining.apprentice", "professions.mining.apprentice.title",
						MiningAction.MiningActionMap.get("professions.mining.action.rapidstrike")));
		FabricRPGPlayerProfessions.registerProfessionGlobally(Profession.ID.MINING,
				new Mining("professions.mining.novice", "professions.mining.novice.title",
						MiningAction.MiningActionMap.get("professions.mining.action.dualweild")));
		FabricRPGPlayerProfessions.registerProfessionGlobally(Profession.ID.MINING,
				new Mining("professions.mining.excavator", "professions.mining.excavator.title",
						MiningAction.MiningActionMap.get("professions.mining.action.deconstruction")));
		FabricRPGPlayerProfessions.registerProfessionGlobally(Profession.ID.MINING,
				new Mining("professions.mining.diamond_hound", "professions.mining.diamond_hound.title",
						MiningAction.MiningActionMap.get("professions.mining.action.bloodscent")));
		System.out.println("COMPLETED --- Registering Fabric RPG --- Professions");
	}

	public void registerItems() {
		System.out.println("Registering Fabric RPG --- Items");
		new Blood();
		new BloodPotion();
		new DnaPotion();
		new ExperiencePotion();
		new ProfessionsTomb();
		new Lightning();
		new LightningSword();
		new Plasma();
		new PlasmaPotion();
		new PortalPotion();
		new Soul();
		new SoulRune();
		new SoulStone();
		new StopStick();
		System.out.println("COMPLETED --- Registering Fabric RPG --- Items");
	}

	public void registerBlocks() {
		System.out.println("Registering Fabric RPG --- Blocks");
		new PortalPlantBlock();
		new PortalHalo();
		System.out.println("COMPLETED --- Registering Fabric RPG --- Blocks");
	}

	public void registerBlockEntities() {
		System.out.println("Registering Fabric RPG --- Block Entities");
		BLOCK_ENTITIES.put("portal_halo_block_entity",
				Registry.register(Registry.BLOCK_ENTITY, new Identifier(FabricRPG.MODID, "portal_halo_block_entity"),
						BlockEntityType.Builder
								.create(PortalHaloBlockEntity::new,
										Registry.BLOCK.get(new Identifier(FabricRPG.MODID, "portal_halo")))
								.build(null)));
		BLOCK_ENTITIES
				.put("portal_plant_block_entity",
						Registry.register(Registry.BLOCK_ENTITY,
								new Identifier(FabricRPG.MODID, "portal_plant_block_entity"),
								BlockEntityType.Builder
										.create(PortalPlantBlockEntity::new,
												Registry.BLOCK
														.get(new Identifier(FabricRPG.MODID, "portal_plant_block")))
										.build(null)));
		System.out.println("COMPLETED --- Registering Fabric RPG --- Block Entities");
	}

	public void registerBlockEntityRenderers() {
		System.out.println("Registering Fabric RPG --- Block Entity Renderers");
		BlockEntityRendererRegistry.INSTANCE.register(PortalHaloBlockEntity.class, new PortalHaloBlockEntityRenderer());
		BlockEntityRendererRegistry.INSTANCE.register(PortalPlantBlockEntity.class,
				new PortalPlantBlockEntityRenderer());
		System.out.println("COMPLETED --- Registering Fabric RPG --- Block Entity Renderers");
	}

	public void registerPackets() {
		System.out.println("Registering Fabric RPG --- Packets");
		new PlayerPortalNetworkConsumer();
		new StatPacketConsumer();
		System.out.println("COMPLETED --- Registering Fabric RPG --- Packets");
	}

	public void registerStats() {
		System.out.println("Registering Fabric RPG --- Stats");
		new FabricRPGAttackSpeedStat().registerStatInStatTypeHashMap();
		new FabricRPGBloodStat().registerStatInStatTypeHashMap();
		new FabricRPGLifeStealStat().registerStatInStatTypeHashMap();
		new FabricRPGSoulStat().registerStatInStatTypeHashMap();
		System.out.println("COMPLETED --- Registering Fabric RPG --- Stats");
	}
}
