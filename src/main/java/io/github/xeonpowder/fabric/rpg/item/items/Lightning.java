package io.github.xeonpowder.fabric.rpg.item.items;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * Plasma Potion
 */
public class Lightning extends FabricRPGItem {
    public Lightning() {
        super(100); // maxDamage
        this.setItemName(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName()));
        this.registerItem();
    }

    @Override
    protected List<String> allowedStats() {
        List<String> allowedStats = new ArrayList<String>();
        return allowedStats;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        world.spawnEntity(new LightningEntity(world, playerEntity.rayTrace(256, 1.0f, true).getPos().x, playerEntity.rayTrace(256, 1.0f, true).getPos().y, playerEntity.rayTrace(256, 1.0f, true).getPos().z, false));
        playerEntity.getMainHandStack().damage(1, playerEntity, (player) -> {
            player.sendToolBreakStatus(player.getActiveHand());
        });
        return super.use(world, playerEntity, hand);
    }
}