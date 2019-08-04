package io.github.xeonpowder.fabric.rpg.server.packet;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

/**
 * PacketRegistrator
 */
public class ServerPacketConsumerRegistrator {

    public ServerPacketConsumerRegistrator(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends ServerPacketConsumer>> serverPacketConsumers = reflections.getSubTypesOf(ServerPacketConsumer.class);
        serverPacketConsumers.forEach(serverPacketConsumer -> {
            try {
                serverPacketConsumer.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
			}
        });
    }
}