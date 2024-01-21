package de.bloon.easywarp.command;

import de.bloon.easywarp.EasyWarp;
import de.bloon.easywarp.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class warp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§4You have to be a player to execute this command!");
            return true;
        }

        Player p = (Player) sender;

        if(args.length != 1) {
            p.sendMessage("§6Please use /warp <Name>");
            return true;
        }

        Location loc = EasyWarp.getInstance().getConfig().getLocation("warps."+args[0]+".loc");
        if(loc == null) {
            p.sendMessage("§cA warp with the name " + args[0] + " does not exists!");
            return true;
        }

        if(p.hasPermission("admin.warp.bypass")) {
            p.teleport(loc);
            p.sendMessage("§aYou have been teleported");
            return true;
        }
        PlayerManager.addPlayer(p.getName(), new BukkitRunnable() {
            @Override
            public void run() {
                PlayerManager.removePlayerWithOutCancel(p.getName());
                YamlConfiguration config = EasyWarp.getInstance().getConfig();

                Location loc = config.getLocation("warps."+args[0]+".loc");
                p.teleport(loc);
                p.sendMessage("§aYou have been teleported");
            }
        }.runTaskLater(EasyWarp.getInstance(), EasyWarp.getInstance().getWait() *EasyWarp.TICK));

        p.sendMessage("§6You'll be teleported in " + EasyWarp.getInstance().getWait() + " seconds. §cDO NOT MOVE!");

        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return EasyWarp.warps;
    }
}
