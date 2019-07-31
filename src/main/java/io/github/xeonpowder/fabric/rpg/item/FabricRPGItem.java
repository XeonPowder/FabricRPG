package io.github.xeonpowder.fabric.rpg.item;

import io.github.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.FabricRPG;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;

public class FabricRPGItem extends Item {
    private String itemName = "";

    public FabricRPGItem() {
        super(new Item.Settings().group(ItemGroup.MISC));
    }

    protected void registerItem() {
        Registry.register(Registry.ITEM, new Identifier(FabricRPG.MODID, getItemName()), this);
    }

    protected void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public FabricRPGItem(String itemName) {
        super(new Item.Settings().group(ItemGroup.MISC));
        this.setItemName(itemName);
        this.registerItem();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Text getName() {
        return new LiteralText(FormattingEngine.replaceColorCodeEnumInString(
                new TranslatableText("item." + FabricRPG.MODID + "." + this.getItemName()).asString()));
    }

    @Override
    public Text getName(ItemStack itemStack) {
        if (itemStack.getItem() instanceof FabricRPGItem) {
            return new LiteralText(FormattingEngine.replaceColorCodeEnumInString(
                    new TranslatableText("item." + FabricRPG.MODID + "." + this.getItemName()).asString()));
        } else {
            return super.getName(itemStack);
        }

    }

    public String getItemName() {
        return this.itemName;
    }
}