package de.bloon.easywarp;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class PlayerManager {
    public static HashMap<String, BukkitTask> waits = new HashMap<>();
    public static void addPlayer(String name, BukkitTask runnable) {
        waits.put(name, runnable);
    }
    public static void removePlayer(String name) {
        waits.get(name).cancel();
        waits.remove(name);
    }

    public static void removePlayerWithOutCancel(String name) {
        waits.remove(name);
    }

    public static boolean isWaiting(String name) {
        return waits.containsKey(name);
    }

}
