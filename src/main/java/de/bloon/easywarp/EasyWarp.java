package de.bloon.easywarp;

import de.bloon.easywarp.command.*;
import de.bloon.easywarp.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class EasyWarp extends JavaPlugin {

    public static int TICK = 20;
    private YamlConfiguration configuration;
    private static EasyWarp easyWarp;
    private File file;
    private boolean cooldown;
    private  long wait;
    public static List<String> warps;
    @Override
    public void onEnable() {
        easyWarp = this;
        warps = new ArrayList<>();
        file = new File("warps.file");
        configuration = YamlConfiguration.loadConfiguration(this.file);
        configuration.addDefault("spawn.cooldown", false);
        configuration.addDefault("wait", 5L);
        cooldown = configuration.getBoolean("spawn.cooldown");
        wait = configuration.getLong("wait");
        warps.addAll(configuration.getStringList("data.warps"));
        // Plugin startup logic

        Bukkit.getPluginCommand("setspawn").setExecutor(new setspawn());
        Bukkit.getPluginCommand("spawn").setExecutor(new spawn());
        Bukkit.getPluginCommand("deletewarp").setExecutor(new deletewarp());
        Bukkit.getPluginCommand("setwarp").setExecutor(new setwarp());
        Bukkit.getPluginCommand("warp").setExecutor(new warp());
        Bukkit.getPluginCommand("warps").setExecutor(new warps());

        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getConsoleSender().sendMessage("§aPlugin loaded");
    }

    public long getWait() {
        return wait;
    }

    public static EasyWarp getInstance() {
        return easyWarp;
    }

    public Boolean getCoolDown() {
        return cooldown;
    }

    public File getFile() {
        return file;
    }

    @Override
    public YamlConfiguration getConfig() {
        return configuration;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
