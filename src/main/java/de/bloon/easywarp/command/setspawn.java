package de.bloon.easywarp.command;

import de.bloon.easywarp.EasyWarp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.event.WindowStateListener;
import java.io.File;

public class setspawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§4You have to be a player to execute this command!");
            return true;
        }

        Player p = (Player) sender;
        if(!p.hasPermission("admin.setspawn")) {
            p.sendMessage("§cAccess denied!");
            return true;
        }

        Location loc = p.getLocation();
        int x = (int) loc.getX(),
                y = (int) loc.getY(),
                z = (int) loc.getZ();
        float pitch = loc.getPitch(), yaw = loc.getYaw();


        EasyWarp.getInstance().getConfig().set("spawn.x", x);
        EasyWarp.getInstance().getConfig().set("spawn.y", y);
        EasyWarp.getInstance().getConfig().set("spawn.z", z);
        EasyWarp.getInstance().getConfig().set("spawn.pitch", pitch);
        EasyWarp.getInstance().getConfig().set("spawn.yaw", yaw);
        EasyWarp.getInstance().getConfig().set("spawn.world", loc.getWorld().getName());
        try {
            EasyWarp.getInstance().getConfig().save(EasyWarp.getInstance().getFile());
            p.sendMessage("§aSpawn location set to " + x + " " + y + " " + z);
        } catch (Exception ignored) {}

        return false;
    }
}
