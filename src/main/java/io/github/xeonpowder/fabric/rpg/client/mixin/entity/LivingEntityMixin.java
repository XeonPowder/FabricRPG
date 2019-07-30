package io.github.xeonpowder.fabric.rpg.client.mixin.entity;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.entity.FabricRPGLivingEntityDB;
import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStack;
import io.github.xeonpowder.fabric.rpg.itemStack.FabricRPGItemStackDB;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

        @Inject(at = @At(value = "RETURN"), method = "<init>*")
        public void registerLivingEntityInFabricRPGLivingEntityDB(CallbackInfo ci) {
                if ((((LivingEntity) (Object) this)) != null) {
                        if (FabricRPG.LivingEntityDB.get((((LivingEntity) (Object) this)).getUuidAsString()) == null) {
                                FabricRPG.LivingEntityDB.put((((LivingEntity) (Object) this)).getUuidAsString(),
                                                new FabricRPGLivingEntityDB(
                                                                (((LivingEntity) (Object) this)).getUuidAsString()));
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
                                if (FabricRPG.LivingEntityDB
                                                .get(((LivingEntity) (Object) this).getUuidAsString()) != null) {
                                        lastPlayerAttacking = FabricRPG.LivingEntityDB
                                                        .get(((LivingEntity) (Object) this).getUuidAsString())
                                                        .getFabricRPGLivingEntity(
                                                                        ((LivingEntity) (Object) this)).lastAttackingPlayer;
                                }
                                if (lastPlayerAttacking == null) {
                                        lastPlayerAttacking = ((PlayerEntity) damageSource.getAttacker());
                                        System.out.println(FabricRPG.asClientOrServer("last player attacking: "
                                                        + lastPlayerAttacking.getDisplayName().asString()));
                                        lastPlayerAttackingWithWhichItemStack = ((PlayerEntity) damageSource
                                                        .getAttacker()).getMainHandStack();
                                        System.out.println("last player attacking Main Hand Stack: "
                                                        + lastPlayerAttackingWithWhichItemStack.getItem().getName()
                                                                        .asString());
                                } else {
                                        lastPlayerAttackingWithWhichItemStack = FabricRPG.LivingEntityDB
                                                        .get(((LivingEntity) (Object) this).getUuidAsString())
                                                        .getFabricRPGLivingEntity(
                                                                        ((LivingEntity) (Object) this)).lastAttackingPlayerItemStack;
                                }
                                try {
                                        // get slot index
                                        if (lastPlayerAttacking.inventory != null) {
                                                int lastPlayerAttackingWithItemStackIsInSlot = lastPlayerAttacking.inventory
                                                                .getSlotWithStack(
                                                                                lastPlayerAttackingWithWhichItemStack);
                                                // copy old itemstack into new itemStack
                                                ItemStack newItemStackForLastAttackingPlayer = lastPlayerAttackingWithWhichItemStack
                                                                .copy();
                                                // create or get FabricRPGItemStack linked to new itemStack
                                                FabricRPGItemStack fabricRPGItemStack = null;
                                                FabricRPGItemStackDB fabricRPGItemStackDB = FabricRPG.ItemStackDB
                                                                .get(lastPlayerAttackingWithWhichItemStack.getItem());
                                                if (fabricRPGItemStackDB == null) {
                                                        FabricRPG.ItemStackDB.put((lastPlayerAttackingWithWhichItemStack
                                                                        .getItem() instanceof FabricRPGItem)
                                                                                        ? ((FabricRPGItem) lastPlayerAttackingWithWhichItemStack
                                                                                                        .getItem())
                                                                                        : lastPlayerAttackingWithWhichItemStack
                                                                                                        .getItem(),
                                                                        new FabricRPGItemStackDB(
                                                                                        (lastPlayerAttackingWithWhichItemStack
                                                                                                        .getItem() instanceof FabricRPGItem)
                                                                                                                        ? ((FabricRPGItem) lastPlayerAttackingWithWhichItemStack
                                                                                                                                        .getItem())
                                                                                                                        : lastPlayerAttackingWithWhichItemStack
                                                                                                                                        .getItem()));
                                                        fabricRPGItemStackDB = FabricRPG.ItemStackDB
                                                                        .get(lastPlayerAttackingWithWhichItemStack
                                                                                        .getItem());

                                                }
                                                fabricRPGItemStack = fabricRPGItemStackDB.getFabricRPGItemStack(
                                                                lastPlayerAttackingWithWhichItemStack);
                                                if (fabricRPGItemStack == null) {
                                                        fabricRPGItemStack = new FabricRPGItemStack(
                                                                        lastPlayerAttackingWithWhichItemStack);
                                                }
                                                // add stats to fabricRPGItemStack
                                                FabricRPG.ItemStackDB
                                                                .get(lastPlayerAttackingWithWhichItemStack.getItem())
                                                                .dropFabricItemStack(fabricRPGItemStack);

                                                fabricRPGItemStack.getStats().addToStat("blood", 1);

                                                lastPlayerAttacking.inventory.main.set(
                                                                lastPlayerAttackingWithItemStackIsInSlot,
                                                                newItemStackForLastAttackingPlayer);

                                                FabricRPG.ItemStackDB.get(newItemStackForLastAttackingPlayer.getItem())
                                                                .putItemStack(fabricRPGItemStack,
                                                                                newItemStackForLastAttackingPlayer);

                                                System.out.println("fabricRPGItemStack hashCode: "
                                                                + fabricRPGItemStack.hashCode());
                                                System.out.println("newItemStackForLastAttackingPlayer: "
                                                                + newItemStackForLastAttackingPlayer.hashCode());
                                                // set the itemStack of the player's inventory slot containing the item
                                                // used to
                                                // attack the living entity updated stats ItemStack

                                                // update inventory
                                                lastPlayerAttacking.inventory.updateItems();
                                        } else {
                                                System.out.println(FabricRPG.asClientOrServer(
                                                                "last player attacking invtory is null "));
                                        }

                                } catch (Exception e) {
                                        System.err.println(e);
                                        // TODO: handle exception
                                }

                        }

                }
        }
}