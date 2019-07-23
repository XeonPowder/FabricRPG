package io.github.xeonpowder.fabric.rpg.client.mixin.entity.passive;

import io.github.xeonpowder.fabric.rpg.client.entity.passive.events.SheepEvents;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.sound.MusicTracker;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@Mixin(SheepEntity.class)
public class SheepEntityMixin extends SheepEntity {
    public SheepEntityMixin(EntityType<? extends SheepEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/SheepEntity;dropItems()V"), method = "interactMob", cancellable = true)
    private void onShear(final PlayerEntity player, final CallbackInfoReturnable<Boolean> info) {
        ActionResult result = SheepEvents.SheepShearCallback.EVENT.invoker().interact(player,
                (SheepEntity) (Object) this);
        if (result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}
