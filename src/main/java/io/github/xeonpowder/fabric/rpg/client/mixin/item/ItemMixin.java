package io.github.xeonpowder.fabric.rpg.client.mixin.item;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItemTooltip;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStack;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStackDB;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(at = @At(value = "RETURN"), method = "<init>*")
    public void addToFabricRPGItemDB(CallbackInfo ci) {
        if (FabricRPG.ItemStackDB.get((Item) (Object) this) == null) {
            FabricRPG.ItemStackDB.put(((Item) (Object) this), new FabricRPGItemStackDB(((Item) (Object) this)));
        }
    }
}