package io.github.xeonpowder.fabric.rpg.client.mixin.entity;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Environment(EnvType.CLIENT)
    @Inject(at = @At(value = "RETURN"), method = "onDeath")
    public void onLivingEntityDeath(DamageSource damageSource, CallbackInfo ci) {
        if (damageSource.getAttacker() != null) {
            System.out.println("living entity died, add blood to item");

            damageSource.getAttacker().getItemsHand().forEach(itemStack -> {
                System.out.println(itemStack.getItem().getName().asString());
                if (!itemStack.getItem().getName().asString().equals("Air")) {
                    System.out.println(FabricRPG.ItemStackDB.get(itemStack.getItem()).getFabricRPGItemStack(itemStack)
                            .getStats().getStatsMap().get("blood").getStatValue());
                    FabricRPGItemStack fabricRPGItemStack = FabricRPG.ItemStackDB.get(itemStack.getItem())
                            .getFabricRPGItemStack(itemStack);
                    fabricRPGItemStack.getStats().addToStat("blood", 1);
                    // FabricRPG.ItemStackDB.get(itemStack.getItem()).putItemStack(fabricRPGItemStack,
                    // itemStack);
                    System.out.println(FabricRPG.ItemStackDB.get(itemStack.getItem()).getFabricRPGItemStack(itemStack)
                            .getStats().getStatsMap().get("blood").getStatValue());
                    System.out.println(itemStack.hashCode());
                }

            });
        }
    }
}