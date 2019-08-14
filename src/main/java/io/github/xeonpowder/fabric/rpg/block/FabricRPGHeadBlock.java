package io.github.xeonpowder.fabric.rpg.block;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * FabricRPGHeadBlock
 */
public class FabricRPGHeadBlock extends FabricRPGBlock {

    public FabricRPGHeadBlock(Settings blockSettings, boolean isTransparent) {
        super(blockSettings, isTransparent);
        this.isTransparent = isTransparent;
    }

    @Override
    public void registerBlock() {
        Registry.register(Registry.BLOCK, new Identifier(FabricRPG.MODID, this.getTranslationKey()), this);
        Registry.register(Registry.ITEM, new Identifier(FabricRPG.MODID, this.getTranslationKey()),
                new FabricRPGBlockItem<FabricRPGHeadBlock>(this,
                        new Item.Settings().group(FabricRPG.ITEM_GROUP).maxDamage(1920)));
    }
}