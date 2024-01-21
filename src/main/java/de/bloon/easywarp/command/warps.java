package de.bloon.easywarp.command;

import de.bloon.easywarp.EasyWarp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class warps implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(EasyWarp.warps.isEmpty()) {
            sender.sendMessage("§cThere a no available warp points!");
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("§6All available warp points").append("\n").append("§b");
        for (String s : EasyWarp.warps) {
            sb.append(s).append("\n");
        }

        sender.sendMessage(sb.toString());
        return false;
    }
}
