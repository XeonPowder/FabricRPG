package io.github.xeonpowder.fabric.rpg.block;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

/**
 * FabricRPGBlockWithEntity
 */
public class FabricRPGBlockWithEntity extends BlockWithEntity {

    protected boolean isTransparent;
    protected Identifier id;

    public FabricRPGBlockWithEntity(Settings blockSettings, boolean isTransparent) {
        super(blockSettings);
        this.isTransparent = isTransparent;
        this.id = new Identifier(FabricRPG.MODID, this.getTranslationKey());
    }

    public void registerBlock() {
        Registry.register(Registry.BLOCK, new Identifier(FabricRPG.MODID, this.getTranslationKey()), this);
        Registry.register(Registry.ITEM, new Identifier(FabricRPG.MODID, this.getTranslationKey()),
                new FabricRPGBlockItem<FabricRPGBlockWithEntity>(this,
                        new Item.Settings().group(FabricRPG.ITEM_GROUP)));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView arg0) {
        return null;
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