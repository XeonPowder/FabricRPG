package io.github.xeonpowder.fabric.rpg.item.items;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * ExperiencePotion
 */
public class ExperiencePotion extends FabricRPGItem {
    public ExperiencePotion() {
        super();
        this.setItemName(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName()));
        this.registerItem();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        System.out.println(playerEntity.totalExperience);
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        if (itemStack.getTag().containsKey("storedEXP")) {
            if (itemStack.getTag().getInt("storedEXP") != 0) {
                playerEntity.addExperience(itemStack.getTag().getInt("storedEXP"));
                itemStack.getTag().putInt("storedExp", 0);
                return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemStack);
            }

        } else if (playerEntity.totalExperience > 0) {
            itemStack.getTag().putInt("storedEXP", playerEntity.totalExperience);
            playerEntity.totalExperience = 0;
            return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemStack);
        }
        return super.use(world, playerEntity, hand);
    }
}