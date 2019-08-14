package io.github.xeonpowder.fabric.rpg.mixin.block.minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession.Profession;
import io.github.xeonpowder.fabric.rpg.profession.professions.mining.Mining;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * StoneMixin
 */
@Mixin(Block.class)
public class StoneMixin {
    @Inject(at = @At(value = "INVOKE"), method = "onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)V")
    public void onStoneBreak(World world, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity,
            CallbackInfo ci) {
        if (!world.isClient) {
            if (playerEntity.getStackInHand(playerEntity.getActiveHand()).getItem() instanceof PickaxeItem) {
                Mining profession = (Mining) FabricRPG.PlayerProfessionsComponent.get(playerEntity)
                        .getPlayerProfessions().getProfession(Profession.ID.MINING);
                if (profession != null && profession.isToggled()) {
                    if (blockState.getBlock() instanceof OreBlock) {
                        profession.consume(world, playerEntity);
                    }

                }
            }
        }
    }

}