package io.github.xeonpowder.fabric.rpg.block;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.minecraft.block.PlayerSkullBlock;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * FabricRPGSkull
 */
public class FabricRPGSkullBlock extends PlayerSkullBlock {
    protected Identifier id;
    protected boolean isTransparent;

    protected FabricRPGSkullBlock(Settings block, boolean isTransparent) {
        super(block);
        this.isTransparent = isTransparent;
    }

    public void registerBlock() {
        Registry.register(Registry.BLOCK, this.getIdentifier(), this);
        Registry.register(Registry.ITEM, this.getIdentifier(),
                new FabricRPGBlockItem<FabricRPGSkullBlock>(this, new Item.Settings().group(FabricRPG.ITEM_GROUP)));
    }

    public String getTranslationKey() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName());
    }

    public static String getTranslationKey(Class<? extends FabricRPGSkullBlock> skullBlockExtended) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, skullBlockExtended.getClass().getSimpleName());
    }

    public Identifier getIdentifier() {
        return this.id;
    }

}