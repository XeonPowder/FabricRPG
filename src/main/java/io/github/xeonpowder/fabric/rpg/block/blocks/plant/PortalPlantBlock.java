package io.github.xeonpowder.fabric.rpg.block.blocks.plant;

import java.util.Random;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.block.FabricRPGPlantBlock;
import io.github.xeonpowder.fabric.rpg.portalnetwork.gui.PortalNetworkClientScreen;
import io.github.xeonpowder.fabric.rpg.portalnetwork.gui.screen.PortalNetworkLightweightGuiDescription;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

/**
 * PortalPlantBlock
 */
public class PortalPlantBlock extends FabricRPGPlantBlock implements BlockEntityProvider {
    public static final int THE_END_DIMENSION_TAX = 15;

    public PortalPlantBlock() {
        // Block.Settings, isTransparent
        super(FabricBlockSettings.of(Material.PLANT).collidable(false).lightLevel(6).build(), true);
        this.id = new Identifier(FabricRPG.MODID, this.getTranslationKey());
        this.registerBlock();
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hitResult) {
        if (world.isClient) {
            openClientPortalNetworkScreen(world, player);
        }
        return true;
    }

    @Environment(EnvType.CLIENT)
    public static void openClientPortalNetworkScreen(World world, PlayerEntity player) {
        net.minecraft.client.MinecraftClient.getInstance()
                .openScreen(new PortalNetworkClientScreen(new PortalNetworkLightweightGuiDescription(world, player)));
    }

    public static int calculateCurrencyCostForTeleport(DimensionType dim1, Vec3d pos1, DimensionType dim2, Vec3d pos2) {
        int requiredPortalPlantCount = 0;
        if (dim2.equals(DimensionType.THE_END) || dim1.equals(DimensionType.THE_END)) {
            requiredPortalPlantCount = THE_END_DIMENSION_TAX;
        } else {
            if (dim2.equals(DimensionType.THE_NETHER)) {
                if (dim1.equals(DimensionType.OVERWORLD)) {
                    pos1 = new Vec3d(pos1.getX() / 8, pos1.getY(), pos1.getY() / 8);
                }
            } else if (dim1.equals(DimensionType.THE_NETHER)) {
                if (dim2.equals(DimensionType.OVERWORLD)) {
                    pos1 = new Vec3d(pos1.getX() * 8, pos1.getY(), pos1.getY() * 8);
                }
            }
            requiredPortalPlantCount = getRequiredPortalPlantCount(pos1, pos2);
        }
        return requiredPortalPlantCount;
    }

    public static int getRequiredPortalPlantCount(Vec3d pos1, Vec3d pos2) {
        return ((int) (Math.floor(Math.sqrt(Math.pow(((pos2.getX() / 16) - (pos1.getX() / 16)), 2)
                + Math.pow(((pos2.getZ() / 16) - (pos1.getZ() / 16)), 2)) + .5)));
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        double double_1 = (double) blockPos_1.getX() + 0.5D;
        double double_2 = (double) blockPos_1.getY() + 0.7D;
        double double_3 = (double) blockPos_1.getZ() + 0.5D;
        world_1.addParticle(ParticleTypes.PORTAL, double_1, double_2, double_3, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new PortalPlantBlockEntity();
    }
}