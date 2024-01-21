package de.bloon.easywarp.command;

import de.bloon.easywarp.EasyWarp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class setwarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§4You have to be a player to execute this command!");
            return true;
        }

        Player p = (Player) sender;

        if(!p.hasPermission("admin.setwarp")) {
            p.sendMessage("§cAccess denied!");
            return true;
        }

        if(args.length != 1) {
            p.sendMessage("§6Please use /setwarp <Name>");
            return true;
        }

        Location loc = p.getLocation();
        loc = round(loc);
        String name = args[0];

        EasyWarp.getInstance().getConfig().set("warps."+name+".loc", loc);
        if(!EasyWarp.warps.contains(args[0]))
            EasyWarp.warps.add(args[0]);
        EasyWarp.getInstance().getConfig().set("data.warps", EasyWarp.warps);
        try {
            EasyWarp.getInstance().getConfig().save(EasyWarp.getInstance().getFile());
            p.sendMessage("§aWarp " + args[0] + " set at " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
        } catch (IOException ignored) {}

        return false;
    }

    private Location round(Location loc) {
        int x = (int) loc.getX(),
                y = (int) loc.getY(),
                z = (int) loc.getZ();
        float pitch = loc.getPitch(),
                yaw = loc.getYaw();

        return new Location(loc.getWorld(),x + 0.5D, y + 0.5D, z + 0.5D, yaw, pitch);
    }
}
