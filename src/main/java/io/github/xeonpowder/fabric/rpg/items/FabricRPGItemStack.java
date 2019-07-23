package io.github.xeonpowder.fabric.rpg.items;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

@Mixin(ItemStack.class)
public class FabricRPGItemStack {
    ItemStack minecraftItemStack;
    FabricRPGItemStackStats stats;

    public FabricRPGItemStack(ItemStack itemStack) {
        this.minecraftItemStack = itemStack;
        this.stats = new FabricRPGItemStackStats();
        this.stats.setBlood(0);
    }

    @Inject(at = @At(value="RETURN"), method = "ItemStack")
    public void setupFabricRPGItemStack(, CallbackInfo ci){
        
    }

    @Inject(at = @At(value = "HEAD"), method = "appendTooltip")
    public void addBlood(ItemStack itemStack, World world, List<Text> tooltipTextList, TooltipContext tooltipContext, CallbackInfo ci) {
        tooltipTextList
        FabricRPGItemTooltip.createTooltip(itemStack.getItem().getName().asString(), tooltipTextList, 70, stats);
    }

}