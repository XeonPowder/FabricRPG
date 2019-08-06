package io.github.xeonpowder.fabric.rpg.client.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(at = @At(value="RETURN"), method="")
    public void onPlayerBreakBlock() {

    }

}
