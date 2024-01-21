package de.bloon.easywarp.command;

import de.bloon.easywarp.EasyWarp;
import de.bloon.easywarp.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.yaml.snakeyaml.Yaml;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§4You have to be a player to execute this command!");
            return true;
        }

        Player p = (Player) sender;

        if(p.hasPermission("admin.warp.bypass")) {
            YamlConfiguration config = EasyWarp.getInstance().getConfig();

            int x = config.getInt("spawn.x"),
                    y = config.getInt("spawn.y"),
                    z = config.getInt("spawn.z");
            float pitch = (float) config.getDouble("spawn.pitch"),
                    yaw = (float) config.getDouble("spawn.yaw");

            String sworld = config.getString("spawn.world");
            World world = Bukkit.getWorld(sworld);

            Location loc = new Location(world, x, y, z, yaw, pitch);
            p.teleport(loc);
            p.sendMessage("§aYou have been teleported");
            return true;
        }

        PlayerManager.addPlayer(p.getName(), new BukkitRunnable() {
            @Override
            public void run() {
                PlayerManager.removePlayerWithOutCancel(p.getName());
                YamlConfiguration config = EasyWarp.getInstance().getConfig();

                int x = config.getInt("spawn.x"),
                        y = config.getInt("spawn.y"),
                        z = config.getInt("spawn.z");
                float pitch = (float) config.getDouble("spawn.pitch"),
                        yaw = (float) config.getDouble("spawn.yaw");

                String sworld = config.getString("spawn.world");
                World world = Bukkit.getWorld(sworld);

                Location loc = new Location(world, x, y, z, yaw, pitch);

                p.teleport(loc);
                p.sendMessage("§aYou have been teleported");
            }
        }.runTaskLater(EasyWarp.getInstance(), EasyWarp.getInstance().getWait() *EasyWarp.TICK));

        p.sendMessage("§6You'll be teleported in " + EasyWarp.getInstance().getWait() + " seconds. §cDO NOT MOVE!");

        return false;
    }
}
