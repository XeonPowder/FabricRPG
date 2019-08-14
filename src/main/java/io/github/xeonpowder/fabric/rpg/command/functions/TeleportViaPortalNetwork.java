package io.github.xeonpowder.fabric.rpg.command.functions;

import java.util.Random;

import com.mojang.brigadier.Command;

import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.block.blocks.plant.PortalPlantBlock;
import net.minecraft.item.BlockItem;

import net.minecraft.item.ItemStack;

import net.minecraft.server.network.ServerPlayerEntity;

import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;

import net.minecraft.util.registry.Registry;

/**
 * TeleportToTarget
 */
public class TeleportViaPortalNetwork {
    private static final BlockItem PortalPlantBlockItem = ((BlockItem) Registry.ITEM
            .get(new Identifier(FabricRPG.MODID, "portal_plant_block")));
    private static final BlockItem PortalHaloBlockItem = ((BlockItem) Registry.ITEM
            .get(new Identifier(FabricRPG.MODID, "portal_halo")));

    public static int player2player(ServerPlayerEntity sourcePlayer, ServerPlayerEntity target) {
        int requiredPortalPlantCount = PortalPlantBlock.calculateCurrencyCostForTeleport(
                sourcePlayer.getServerWorld().getDimension().getType(), sourcePlayer.getPos(),
                target.getServerWorld().getDimension().getType(), target.getPos());
        // armor.get(3) == HEAD Slot
        ItemStack playerHeadItemStack = sourcePlayer.inventory.armor.get(3);
        int countOfPortalPlantBlockInInv = sourcePlayer.inventory.countInInv(PortalPlantBlockItem);
        boolean shouldTeleport = false;
        if (playerHeadItemStack.getItem() == PortalHaloBlockItem) {
            int playerHeadItemStackDurability = (playerHeadItemStack.getMaxDamage() - playerHeadItemStack.getDamage());
            countOfPortalPlantBlockInInv = playerHeadItemStackDurability;
            if (playerHeadItemStackDurability >= requiredPortalPlantCount) {
                playerHeadItemStack.damage(requiredPortalPlantCount, new Random(), sourcePlayer);
                shouldTeleport = true;
            }
        } else if (countOfPortalPlantBlockInInv >= requiredPortalPlantCount) {
            DefaultedList<ItemStack> inventoryAsList = sourcePlayer.inventory.main;
            int countRemoved = 0;
            for (int x = 0; x < inventoryAsList.size(); x++) {
                if (countRemoved < requiredPortalPlantCount) {
                    ItemStack itemStack = inventoryAsList.get(x);
                    if (itemStack.getItem().equals(PortalPlantBlockItem)) {
                        int itemStackCount = itemStack.getCount();
                        if (itemStackCount >= requiredPortalPlantCount) {
                            countRemoved = requiredPortalPlantCount;
                            sourcePlayer.inventory.takeInvStack(x, countRemoved);
                        } else {
                            sourcePlayer.inventory.removeInvStack(x);
                            countRemoved += itemStackCount;
                        }
                    }
                }
            }
            shouldTeleport = true;
        }
        if (shouldTeleport) {
            sourcePlayer.sendMessage(new LiteralText(
                    FormattingEngine.replaceColorCodeEnumInString(new TranslatableText("command.fabric_rpg.teleporting",
                            requiredPortalPlantCount, target.getDisplayName().asString()).asString())));

            Teleport.player2player(sourcePlayer, target);
        } else {
            sourcePlayer.sendMessage(new LiteralText(FormattingEngine.replaceColorCodeEnumInString(
                    new TranslatableText("command.fabric_rpg.notEnoughPortalPlantsToTeleport", requiredPortalPlantCount,
                            countOfPortalPlantBlockInInv).asString())));
        }
        return Command.SINGLE_SUCCESS;
    }
}