package io.github.xeonpowder.fabric.rpg.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.List;
import java.util.Locale;

import io.github.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;

public class FabricRPGItem extends Item {
    private String itemName = "";

    public FabricRPGItem(String itemName) {
        super(new Item.Settings().group(ItemGroup.MISC));
        this.itemName = itemName;
        Registry.register(Registry.ITEM, new Identifier(FabricRPG.MODID, getItemName()), this);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }

    public String getItemName() {
        return this.itemName;
    }

    @Override
    public void appendTooltip(ItemStack itemStack_1, World world_1, List<Text> tooltipTextList,
            TooltipContext tooltipContext_1) {
        // List<String> wrappedLocalizedTextAsStringList =
        // StringExtras.wrapForTooltips(localizedText);
        FabricRPGItemTooltip.createTooltip(this.getItemName(), tooltipTextList, 70);
    }
}