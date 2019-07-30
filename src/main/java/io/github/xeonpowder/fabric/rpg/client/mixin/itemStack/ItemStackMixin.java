package io.github.xeonpowder.fabric.rpg.client.mixin.itemStack;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltip;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltipCallback;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStack;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(at = @At(value = "RETURN"), method = "<init>*")
    public void setupItemStackMixin(CallbackInfo ci) {
        FabricRPG.ItemStackDB.get((Item) ((ItemStack) (Object) this).getItem())
                .attachFabricRPG(((ItemStack) (Object) this));
    }

    // public void appendTooltip(ItemStack itemStack, World world, List<Text>
    // tooltipTextList,
    // TooltipContext tooltipContext) {
    // if (itemStack != null) {
    // FabricRPGItemStack rpgItemStack;
    // boolean isRPGItem = false;
    // if (itemStack.getItem() instanceof FabricRPGItem) {
    // rpgItemStack = FabricRPG.ItemStackDB.get((FabricRPGItem) itemStack.getItem())
    // .getFabricRPGItemStack(itemStack);
    // isRPGItem = true;
    // } else {
    // rpgItemStack =
    // FabricRPG.ItemStackDB.get(itemStack.getItem()).getFabricRPGItemStack(itemStack);
    // }

    // if (rpgItemStack.getStats().getStatsMap().get("blood").getStatValue() != 0.0)
    // {
    // System.out.println("rpgItemStack hashCode: " + rpgItemStack.hashCode());
    // System.out.println("itemStack hashCode: " + itemStack.hashCode());
    // }
    // if (isRPGItem) {
    // FabricRPGItemTooltip.createTooltipWithStatsForFabricRPGItem(
    // ((FabricRPGItem) itemStack.getItem()).getItemName(), tooltipTextList, 70,
    // rpgItemStack.getStats().getStatsMap());
    // } else {

    // FabricRPGItemTooltip.createTooltipWithStatsForNonFabricRPGItem(tooltipTextList,
    // 70,
    // rpgItemStack.getStats().getStatsMap(), itemStack.getItem());
    // }
    // }
    // }

    @Inject(at = @At(value = "RETURN"), method = "getTooltip")
    public void appendFabricRPGTooltip(PlayerEntity playerEntity, TooltipContext tooltipContext,
            CallbackInfoReturnable<List<Text>> info, List<Text> lines) {
        System.out.println("getTooltipMixin called!");
        FabricRPGItemTooltipCallback.EVENT.invoker().getTooltip((ItemStack) (Object) this, tooltipContext, lines);

    }
}