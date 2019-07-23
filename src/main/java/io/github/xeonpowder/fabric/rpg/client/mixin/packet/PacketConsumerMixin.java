package io.github.xeonpowder.fabric.rpg.client.mixin.packet;

import java.util.Set;

import org.reflections.Reflections;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.client.packet.ClientPacketConsumer;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

@Mixin(FabricRPG.class)
public class PacketConsumerMixin {
    @Inject(at = @At("HEAD"), method = "onInitialize()V")
    private void onInitialize(CallbackInfo ci) {
        Reflections reflections = new Reflections("io.github.xeonpowder.fabric.mixins.client.packets");
        Set<Class<? extends ClientPacketConsumer>> packetConsumers = reflections
                .getSubTypesOf(ClientPacketConsumer.class);
        for (Class<? extends ClientPacketConsumer> packetConsumer : packetConsumers) {
            try {
                ClientPacketConsumer consumer = packetConsumer.getDeclaredConstructor().newInstance();
                ClientSidePacketRegistry.INSTANCE.register(consumer.id, consumer::accept);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
