package io.github.siebrenvde.staffchat.commands.spigot;

import io.github.siebrenvde.staffchat.Spigot;
import io.github.siebrenvde.staffchat.discord.SpigotAddon;
import io.github.siebrenvde.staffchat.util.SpigotUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpOp implements CommandExecutor {

    private Spigot plugin;
    private SpigotAddon addon;

    public HelpOp(Spigot pl) {
        plugin = pl;
        addon = pl.addon;
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(strings.length < 1) {
                player.sendMessage(ChatColor.RED + "Usage: /helpop <message>");
            }

            else {

                String playerName = player.getName();
                String message = String.join(" ", strings);

                if(player.hasPermission("helpop.see")) {
                    SpigotUtils.sendPermissionMessage(plugin.homLayout(message, playerName), "helpop.see");
                }

                else {
                    player.sendMessage(plugin.homLayout(message, playerName));

                    SpigotUtils.sendPermissionMessage(plugin.homLayout(message, playerName), "helpop.see");
                }

                if(plugin.getConfig().getBoolean("use-embed")) {
                    addon.sendEmbed(
                            "**HelpOp**",
                            "**Player**: " + playerName +
                                    "\n**Message**: `" + message + "`");
                }
                else {
                    addon.sendMessage(plugin.homdLayout(message, playerName));
                }

            }

        }

        else {

            sender.sendMessage("The console can't use this command.");

        }

        return false;
    }
}
