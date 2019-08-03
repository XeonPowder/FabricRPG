package io.github.xeonpowder.fabric.rpg.block;

import com.google.common.base.CaseFormat;

import io.github.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

/**
 * FabricRPGBlockWithEntity
 */
public class FabricRPGBlockWithEntity extends BlockWithEntity {

    private boolean isTransparent;

    public FabricRPGBlockWithEntity(Settings blockSettings, boolean isTransparent) {
        super(blockSettings);
        this.isTransparent = isTransparent;
    }

    public void registerBlock() {
        Registry.register(Registry.BLOCK, new Identifier(FabricRPG.MODID, this.getTranslationKey()), this);
        Registry.register(Registry.ITEM, new Identifier(FabricRPG.MODID, this.getTranslationKey()),
                new FabricRPGBlockItem<FabricRPGBlockWithEntity>(this, new Item.Settings().group(ItemGroup.MISC)));
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
}