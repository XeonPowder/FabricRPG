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
        Set<Class<? extends Object>> serverPacketConsumers = reflections.getSubTypesOf(Object.class);
        System.out.println(serverPacketConsumers.toString());
        serverPacketConsumers.forEach(serverPacketConsumer -> {
            try {
                System.out.println(serverPacketConsumer.getSimpleName());
                serverPacketConsumer.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
			}
        });
    }
}