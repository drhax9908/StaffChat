package io.github.siebrenvde.staffchat.events;

import io.github.siebrenvde.staffchat.Bungee;
import io.github.siebrenvde.staffchat.discord.BungeeAddon;
import io.github.siebrenvde.staffchat.util.BungeeUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeMessageEvent implements Listener {

    private Bungee plugin;
    private BungeeAddon addon;

    public BungeeMessageEvent(Bungee pl) {
        plugin = pl;
        addon = pl.addon;
    }

    @EventHandler
    public void onPlayerChat(ChatEvent event) {

        if(event.getSender() instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer) event.getSender();

            if(plugin.toggledPlayers.contains(player)) {

                if(!event.getMessage().startsWith("/")) {

                    event.setCancelled(true);
                    String server = player.getServer().getInfo().getName();
                    BungeeUtils.sendPermissionMessage(plugin.generalLayout(event.getMessage(), player.getName(), player.getDisplayName(), server), "staffchat.see");
                    addon.sendMessage(plugin.discordLayout(event.getMessage(), player.getName(), player.getDisplayName(), server));

                }

            }

        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerDisconnectEvent event) {
        plugin.toggledPlayers.remove(event.getPlayer());
    }

}
