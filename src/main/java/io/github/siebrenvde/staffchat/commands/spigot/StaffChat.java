package io.github.siebrenvde.staffchat.commands.spigot;

import io.github.siebrenvde.staffchat.Spigot;
import io.github.siebrenvde.staffchat.discord.SpigotAddon;
import io.github.siebrenvde.staffchat.util.SpigotUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

    private Spigot plugin;
    public StaffChat(Spigot pl) { plugin = pl; }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        SpigotAddon addon = SpigotAddon.getInstance();
        String msg = String.join(" ", strings);

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(player.hasPermission("staffchat.use")) {

                if(strings.length == 0) {
                    player.sendMessage(ChatColor.RED + "Please enter a message!");
                }

                else if(strings.length == 1 && strings[0].equalsIgnoreCase("toggle")) {
                    if(plugin.toggledPlayers.contains(player)) {
                        plugin.toggledPlayers.remove(player);
                        player.sendMessage(ChatColor.RED + "StaffChat toggled off");
                    } else {
                        plugin.toggledPlayers.add(player);
                        player.sendMessage(ChatColor.GREEN + "StaffChat toggled on");
                    }
                }

                else {

                    SpigotUtils.sendPermissionMessage(plugin.generalLayout(msg, player.getName(), player.getDisplayName()), "staffchat.see");
                    addon.sendMessage(plugin.discordLayout(msg, player.getName(), player.getDisplayName()));
                }

            } else { player.sendMessage(SpigotUtils.permissionMessage); }

        } else {
            SpigotUtils.sendPermissionMessage(plugin.generalLayout(msg, "CONSOLE", "CONSOLE"), "staffchat.see");
            addon.sendMessage(plugin.discordLayout(msg, "CONSOLE", "CONSOLE"));
        }

        return false;
    }

}
