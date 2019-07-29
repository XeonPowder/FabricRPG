package io.github.xeonpowder.fabric.rpg.item;

import java.util.List;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;

public class FabricRPGItem extends Item {
    private String itemName = "";
    private TooltipContext tooltipContext;

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
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltipTextList,
            TooltipContext tooltipContext) {
        this.tooltipContext = tooltipContext;
        if (itemStack != null) {
            appendFabricRPGTooltip(new FabricRPGItemStack(itemStack), tooltipTextList);
        }

    }

    public void appendFabricRPGTooltip(FabricRPGItemStack itemStack, List<Text> tooltipTextList) {
        if (itemStack != null && itemStack.getStats() != null) {
            FabricRPGItemTooltip.createTooltipWithStats(this.getItemName(), tooltipTextList, 70,
                    itemStack.getStats().getStatsMap());
        }

    }
}