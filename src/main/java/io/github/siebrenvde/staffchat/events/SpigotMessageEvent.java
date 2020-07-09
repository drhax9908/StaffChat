package io.github.siebrenvde.staffchat.events;

import io.github.siebrenvde.staffchat.Spigot;
import io.github.siebrenvde.staffchat.discord.SpigotAddon;
import io.github.siebrenvde.staffchat.util.SpigotUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpigotMessageEvent implements Listener {

    private Spigot plugin;

    public SpigotMessageEvent(Spigot pl) {
        plugin = pl;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        SpigotAddon addon = SpigotAddon.getInstance();
        Player player = event.getPlayer();

        if(plugin.toggledPlayers.contains(player)) {

            event.setCancelled(true);
            SpigotUtils.sendPermissionMessage(plugin.generalLayout(event.getMessage(), player.getName(), player.getDisplayName()), "staffchat.see");
            addon.sendMessage(plugin.discordLayout(event.getMessage(), player.getName(), player.getDisplayName()));

        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.toggledPlayers.remove(event.getPlayer());
    }

}
