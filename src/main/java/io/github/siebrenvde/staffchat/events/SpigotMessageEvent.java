package io.github.siebrenvde.staffchat.events;

import io.github.siebrenvde.staffchat.Spigot;
import io.github.siebrenvde.staffchat.util.SpigotUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SpigotMessageEvent implements Listener {

    private Spigot plugin;

    public SpigotMessageEvent(Spigot pl) {
        plugin = pl;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if(plugin.toggledPlayers.contains(player)) {

            event.setCancelled(true);
            SpigotUtils.sendPermissionMessage(event.getMessage(), "staffchat.see");

        }

    }

}
