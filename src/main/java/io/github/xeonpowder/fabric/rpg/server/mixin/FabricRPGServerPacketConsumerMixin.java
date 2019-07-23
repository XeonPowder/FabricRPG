package io.github.xeonpowder.fabric.rpg.server.mixin;

import java.util.Set;

import org.reflections.Reflections;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.server.FabricRPGServerPacketConsumer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

@Mixin(FabricRPG.class)
public class FabricRPGServerPacketConsumerMixin {

    @Inject(at = @At("HEAD"), method = "onInitialize()V")
    private void onInitialize(CallbackInfo ci) {
        Reflections reflections = new Reflections("io.github.xeonpowder.fabric.server.packets");
        Set<Class<? extends FabricRPGServerPacketConsumer>> fabricRPGPacketConsumers = reflections
                .getSubTypesOf(FabricRPGServerPacketConsumer.class);
        for (Class<? extends FabricRPGServerPacketConsumer> fabricRPGPacketConsumer : fabricRPGPacketConsumers) {
            try {
                FabricRPGServerPacketConsumer consumer = fabricRPGPacketConsumer.getDeclaredConstructor().newInstance();
                ServerSidePacketRegistry.INSTANCE.register(consumer.id, consumer::accept);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
