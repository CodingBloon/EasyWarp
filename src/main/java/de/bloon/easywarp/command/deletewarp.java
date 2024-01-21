package de.bloon.easywarp.command;

import de.bloon.easywarp.EasyWarp;
import de.bloon.easywarp.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class deletewarp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            if(args.length != 1) {
                sender.sendMessage("§6Please use /deletewarp <name>");
                return true;
            }

            if(EasyWarp.getInstance().getConfig().getLocation("warps."+args[0]+".loc") == null) {
                sender.sendMessage("§cThis warp does not exist!");
                return true;
            }
            EasyWarp.getInstance().getConfig().set("warps."+args[0]+".loc", null);
            EasyWarp.warps.remove(args[0]);
            EasyWarp.getInstance().getConfig().set("data.warps", EasyWarp.warps);
            try {
                EasyWarp.getInstance().getConfig().save(EasyWarp.getInstance().getFile());
                sender.sendMessage("§aWarp " + args[0] + " deleted");
            } catch (IOException ignored) {}
            return true;
        }

        Player p = (Player) sender;

        if(!p.hasPermission("admin.deletewarp")) {
            p.sendMessage("§cYou can't do this!");
            return true;
        }

        if(args.length != 1) {
            sender.sendMessage("§6Please use /deletewarp <name>");
            return true;
        }

        if(EasyWarp.getInstance().getConfig().getLocation("warps."+args[0]+".loc") == null) {
            sender.sendMessage("§cThis warp does not exist!");
            return true;
        }
        EasyWarp.getInstance().getConfig().set("warps."+args[0]+".loc", null);
        EasyWarp.warps.remove(args[0]);
        EasyWarp.getInstance().getConfig().set("data.warps", EasyWarp.warps);
        try {
            EasyWarp.getInstance().getConfig().save(EasyWarp.getInstance().getFile());
            sender.sendMessage("§aWarp " + args[0] + " deleted");
        } catch (IOException ignored) {}
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return EasyWarp.warps;
    }
}
