package io.github.xeonpowder.fabric.rpg.block;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FabricRPGFlowerBlock extends FlowerBlock {

    protected boolean isTransparent;
    protected Identifier id;

    public FabricRPGFlowerBlock(StatusEffect statusEffect, int int_1, Block.Settings blockSettings,
            boolean isTransparent) {
        super(statusEffect, int_1, blockSettings);
        this.isTransparent = isTransparent;
        this.id = new Identifier(FabricRPG.MODID, this.getTranslationKey());
    }

    public void registerBlock() {
        Registry.register(Registry.BLOCK, new Identifier(FabricRPG.MODID, this.getTranslationKey()), this);
        Registry.register(Registry.ITEM, new Identifier(FabricRPG.MODID, this.getTranslationKey()),
                new FabricRPGBlockItem<FabricRPGFlowerBlock>(this, new Item.Settings().group(FabricRPG.ITEM_GROUP)));
    }

    protected boolean canPlantOnTop(net.minecraft.block.BlockState blockState_1,
            net.minecraft.world.BlockView blockView_1, net.minecraft.util.math.BlockPos blockPos_1) {
        return false;
    }

    public void randomDisplayTick(net.minecraft.block.BlockState blockState_1, net.minecraft.world.World world_1,
            net.minecraft.util.math.BlockPos blockPos_1, java.util.Random random_1) {
    }

    public void onEntityCollision(net.minecraft.block.BlockState blockState_1, net.minecraft.world.World world_1,
            net.minecraft.util.math.BlockPos blockPos_1, net.minecraft.entity.Entity entity_1) {
    }

    @Environment(EnvType.CLIENT)
    public BlockRenderLayer getRenderLayer() {

        return this.isTransparent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
    }

    public String getTranslationKey() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName());
    }

    public static String getTranslationKey(Class<? extends FabricRPGPlantBlock> plantBlockExtended) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, plantBlockExtended.getClass().getSimpleName());
    }

    public Identifier getIdentifier() {
        return this.id;
    }
}