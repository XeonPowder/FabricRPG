package io.github.xeonpowder.fabric.rpg.client.packet;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

/**
 * PacketRegistrator
 */
public class ClientPacketConsumerRegistrator {

    public ClientPacketConsumerRegistrator(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends ClientPacketConsumer>> clientPacketConsumers = reflections.getSubTypesOf(ClientPacketConsumer.class);
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