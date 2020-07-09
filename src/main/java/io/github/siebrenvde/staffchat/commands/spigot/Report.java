package io.github.siebrenvde.staffchat.commands.spigot;

import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import io.github.siebrenvde.staffchat.Spigot;
import io.github.siebrenvde.staffchat.discord.SpigotAddon;
import io.github.siebrenvde.staffchat.util.SpigotUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Report implements CommandExecutor {

    private Spigot plugin;
    public Report(Spigot pl) { plugin = pl; }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(sender instanceof Player) {

            SpigotAddon addon = SpigotAddon.getInstance();
            Player player = (Player) sender;

            if(strings.length < 2) {
                player.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.RED + "/report <player> <reason>");
            }

            else {

                Player reportedPlayer = plugin.getServer().getPlayerExact(strings[0]);

                if(reportedPlayer != null) {

                    String reporter = player.getName();
                    String reported = reportedPlayer.getName();

                    String reason = String.join(" ", strings).replaceFirst("(?i)" + reportedPlayer.getName() + " ", "");

                    player.sendMessage(ChatColor.GRAY + "Reported " + ChatColor.RED + reported + ChatColor.GRAY + " for " + ChatColor.RED + reason + ChatColor.GRAY + ".");

                    SpigotUtils.sendPermissionMessage(plugin.rmLayout(reason, reporter, reported), "report.see");

                    if(plugin.getConfig().getBoolean("use-embed")) {
                        addon.sendEmbed(
                                "**Report**",
                                "**Reported player**: " + reported +
                                        "\n**Reporter**: " + reporter +
                                        "\n**Reason**: `" + reason + "`");
                    }
                    else {
                        addon.sendMessage(plugin.rmdLayout(reason, reporter, reported));
                    }

                }

                else {
                    player.sendMessage(ChatColor.RED + strings[0] + ChatColor.GRAY + " is not online.");
                }

            }

        }

        else {

            sender.sendMessage("The console can't use this command.");

        }

        return false;
    }
}
