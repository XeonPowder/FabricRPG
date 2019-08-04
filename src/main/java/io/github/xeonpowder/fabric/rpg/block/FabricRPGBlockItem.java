package io.github.xeonpowder.fabric.rpg.block;

import com.google.common.base.CaseFormat;

import io.github.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

/**
 * FabricRPGBlockItem
 */
public class FabricRPGBlockItem<T extends Block> extends BlockItem {
    private T block;

    public FabricRPGBlockItem(T block, Settings itemSettings) {
        super(block, itemSettings);
        this.block = block;
    }

    public T getBlock() {
        return this.block;
    }
    
    @Override
    public String getTranslationKey() {
        return this.getBlock().getTranslationKey().substring(this.getBlock().getTranslationKey().lastIndexOf(".")+1);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Text getName() {
        System.out.println("--- never called");
        return new LiteralText(FormattingEngine.replaceColorCodeEnumInString(
                new TranslatableText("block." + FabricRPG.MODID + "." + this.getTranslationKey()).asString()));
    }

    @Override
    public Text getName(ItemStack itemStack) {
        if (itemStack.getItem() instanceof FabricRPGBlockItem) {
            return new LiteralText(FormattingEngine.replaceColorCodeEnumInString(
                    new TranslatableText("block." + FabricRPG.MODID + "." + this.getTranslationKey()).asString()));
        } else {
            return super.getName(itemStack);
        }

    }
    public static String getTranslationKey(Class<? extends FabricRPGPlantBlock> plantBlockExtended) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, plantBlockExtended.getClass().getSimpleName());
    }

}