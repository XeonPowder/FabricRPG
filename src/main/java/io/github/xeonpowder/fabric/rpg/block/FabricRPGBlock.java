package io.github.xeonpowder.fabric.rpg.block;

import com.google.common.base.CaseFormat;

import io.github.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.Block;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.BlockRenderLayer;

public class FabricRPGBlock extends Block {
    private boolean isTransparent;

    public FabricRPGBlock(Settings blockSettings, boolean isTransparent) {
        super(blockSettings);
        this.isTransparent = isTransparent;
    }

    public void registerBlock() {
        Registry.register(Registry.BLOCK, new Identifier(FabricRPG.MODID, this.getTranslationKey()), this);
        Registry.register(Registry.ITEM, new Identifier(FabricRPG.MODID, this.getTranslationKey()),
                new FabricRPGBlockItem<FabricRPGBlock>(this, new Item.Settings().group(ItemGroup.MISC)));
    }

    @Environment(EnvType.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return this.isTransparent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
    }

    @Override
    public String getTranslationKey() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName());
    }
}