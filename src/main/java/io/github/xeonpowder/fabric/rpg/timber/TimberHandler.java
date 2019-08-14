package io.github.xeonpowder.fabric.rpg.timber;

import net.fabricmc.fabric.api.event.server.ServerTickCallback;

import java.util.ArrayList;

public class TimberHandler {
    private static ArrayList<FutureLogBreak> breakList;

    static {
        breakList = new ArrayList<>();

        ServerTickCallback.EVENT.register(tick -> {
            for (int i = 0; i < breakList.size(); i++) {
                FutureLogBreak leafBreak = breakList.get(i);
                if (leafBreak.getElapsedTime() >= leafBreak.getMaxTime()) {
                    breakLogBlock(leafBreak);
                    breakList.remove(leafBreak);
                    LogBreaker.onBreak(leafBreak.getWorld(), leafBreak.getPos());
                } else
                    leafBreak.setElapsedTime(leafBreak.getElapsedTime() + 1);
            }
        });
    }

    public static void init() {

    }

    public static void addFutureBreak(FutureLogBreak futureBreak) {
        breakList.add(futureBreak);
    }

    private static void breakLogBlock(FutureLogBreak futureBreak) {
        futureBreak.getWorld().breakBlock(futureBreak.getPos(), true);
    }
}