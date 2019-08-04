package io.github.xeonpowder.fabric.rpg.client.packet;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

/**
 * PacketRegistrator
 */
public class PacketConsumerRegistrator {

    public PacketConsumerRegistrator() {
        Reflections reflections = new Reflections("io.github.xeonpowder.fabric.rpg.client.packet.consumer");
        Set<Class<? extends ClientPacketConsumer>> clientPacketConsumers = reflections.getSubTypesOf(ClientPacketConsumer.class);
        System.out.println(clientPacketConsumers.toString());
        clientPacketConsumers.forEach(clientPacketConsumer -> {
            try {
                clientPacketConsumer.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
			}
        });
    }
}