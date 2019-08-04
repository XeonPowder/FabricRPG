package io.github.xeonpowder.fabric.rpg.block.blocks.plant;

import io.github.cottonmc.cotton.gui.client.ClientCottonScreen;
import io.github.cottonmc.cotton.gui.client.CottonScreen;
import io.github.xeonpowder.fabric.rpg.block.FabricRPGPlantBlock;
import io.github.xeonpowder.fabric.rpg.gui.PortalNetworkClientScreen;
import io.github.xeonpowder.fabric.rpg.gui.controller.PortalNetworkController;
import io.github.xeonpowder.fabric.rpg.gui.screen.PortalNetworkLightweightGuiDescription;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.impl.client.gui.ScreenProviderRegistryImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

/**
 * PortalPlantBlock
 */
public class PortalPlantBlock extends FabricRPGPlantBlock{

    public PortalPlantBlock() {
        // Block.Settings, isTransparent
        super(FabricBlockSettings.of(Material.PLANT).collidable(false).build(), true);
        this.registerBlock();
    }
    
    @Override
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		if (world.isClient) {
            openClientPortalNetworkScreen(world, player);
        } else {
            System.out.println("activate portal plant block --SERVER");
        }
		return true;
    }
    
    @Environment(EnvType.CLIENT)
    public static void openClientPortalNetworkScreen(World world, PlayerEntity player) {
        net.minecraft.client.MinecraftClient.getInstance().openScreen(new PortalNetworkClientScreen(new PortalNetworkLightweightGuiDescription(world, player)));
    }

}