package io.github.xeonpowder.fabric.rpg.client.mixin.item;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltip;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStack;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStackDB;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(at = @At(value = "RETURN"), method = "appendTooltip")
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltipTextList,
            TooltipContext tooltipContext, CallbackInfo ci) {
        if (FabricRPG.ItemStackDB.get((Item) (Object) this) == null) {
            FabricRPG.ItemStackDB.put(((Item) (Object) this), new FabricRPGItemStackDB(((Item) (Object) this)));
        }
        if (itemStack != null) {
            FabricRPGItemStack rpgItemStack = FabricRPG.ItemStackDB.get((Item) (Object) this)
                    .getFabricRPGItemStack(itemStack);
            FabricRPGItemTooltip.createTooltipWithStats(tooltipTextList, 70, rpgItemStack.getStats().getStatsMap());
        }

    }
}