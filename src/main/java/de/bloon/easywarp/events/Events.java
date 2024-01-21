package de.bloon.easywarp.events;

import de.bloon.easywarp.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Events implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(event == null)
            return;
        Player p = event.getPlayer();
        if(PlayerManager.isWaiting(p.getName())) {
            PlayerManager.removePlayer(p.getName());
            p.sendMessage("§cTeleport canceled!");
            return;
        }
    }

}
