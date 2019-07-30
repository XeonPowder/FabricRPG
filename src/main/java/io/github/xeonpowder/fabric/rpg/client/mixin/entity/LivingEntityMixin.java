package io.github.xeonpowder.fabric.rpg.client.mixin.entity;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.entity.FabricRPGLivingEntityDB;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At(value = "RETURN"), method = "<init>*")
    public void registerLivingEntityInFabricRPGLivingEntityDB(CallbackInfo ci) {
        if ((((LivingEntity) (Object) this)) != null) {
            if (FabricRPG.LivingEntityDB.get((((LivingEntity) (Object) this)).getUuidAsString()) == null) {
                FabricRPG.LivingEntityDB.put((((LivingEntity) (Object) this)).getUuidAsString(),
                        new FabricRPGLivingEntityDB((((LivingEntity) (Object) this)).getUuidAsString()));
            }
        }

    }

    @Inject(at = @At(value = "RETURN"), method = "onDeath")
    public void onLivingEntityDeath(DamageSource damageSource, CallbackInfo ci) {
        if (damageSource.getAttacker() != null) {
            System.out.println("living entity died, add blood to item");
            if (damageSource.getAttacker() instanceof PlayerEntity) {
                System.out.println("attacker is instance of player entity");
                ItemStack lastPlayerAttackingWithWhichItemStack = null;
                PlayerEntity lastPlayerAttacking = null;
                if (FabricRPG.LivingEntityDB.get(((LivingEntity) (Object) this).getUuidAsString()) != null) {
                    lastPlayerAttacking = FabricRPG.LivingEntityDB.get(((LivingEntity) (Object) this).getUuidAsString())
                            .getFabricRPGLivingEntity(((LivingEntity) (Object) this)).lastAttackingPlayer;
                }
                if (lastPlayerAttacking == null) {
                    lastPlayerAttacking = ((PlayerEntity) damageSource.getAttacker());
                    System.out.println("last player attacking: " + lastPlayerAttacking.getDisplayName());
                    lastPlayerAttackingWithWhichItemStack = ((PlayerEntity) damageSource.getAttacker())
                            .getMainHandStack();
                    System.out.println("last player attacking Main Hand Stack: "
                            + lastPlayerAttackingWithWhichItemStack.getName());
                } else {
                    lastPlayerAttackingWithWhichItemStack = FabricRPG.LivingEntityDB
                            .get(((LivingEntity) (Object) this).getUuidAsString())
                            .getFabricRPGLivingEntity(((LivingEntity) (Object) this)).lastAttackingPlayerItemStack;
                }

                // get slot index
                int lastPlayerAttackingWithItemStackIsInSlot = lastPlayerAttacking.inventory
                        .getSlotWithStack(lastPlayerAttackingWithWhichItemStack);
                // create new itemStack
                ItemStack newItemStackForLastAttackingPlayer = new ItemStack(
                        lastPlayerAttackingWithWhichItemStack.getItem());
                // create or get FabricRPGItemStack linked to new itemStack
                FabricRPGItemStack fabricRPGItemStack = FabricRPG.ItemStackDB
                        .get(lastPlayerAttackingWithWhichItemStack.getItem())
                        .getFabricRPGItemStack(newItemStackForLastAttackingPlayer);
                // add stats to fabricRPGItemStack
                FabricRPG.ItemStackDB.get(lastPlayerAttackingWithWhichItemStack.getItem())
                        .dropFabricItemStack(fabricRPGItemStack);
                fabricRPGItemStack.getStats().addToStat("blood", 1);

                FabricRPG.ItemStackDB.get(newItemStackForLastAttackingPlayer.getItem()).putItemStack(fabricRPGItemStack,
                        newItemStackForLastAttackingPlayer);
                System.out.println("fabricRPGItemStack hashCode: " + fabricRPGItemStack.hashCode());
                System.out.println(
                        "newItemStackForLastAttackingPlayer: " + newItemStackForLastAttackingPlayer.hashCode());
                // set the itemStack of the player's inventory slot containing the item used to
                // attack the living entity updated stats ItemStack
                lastPlayerAttacking.inventory.main.set(lastPlayerAttackingWithItemStackIsInSlot,
                        newItemStackForLastAttackingPlayer);
                // update inventory
                lastPlayerAttacking.inventory.updateItems();

            }

        }
    }
}