package io.github.siebrenvde.staffchat.discord;

import io.github.siebrenvde.staffchat.Bungee;
import io.github.siebrenvde.staffchat.util.BungeeUtils;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListenerBungee extends ListenerAdapter {

    private Bungee plugin;

    MessageListenerBungee(Bungee pl) {
        plugin = pl;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User author = event.getAuthor();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();

        if(event.isFromType(ChannelType.TEXT)) {

            TextChannel channel = event.getTextChannel();

            if(channel.getId().equals(plugin.config.getString("staff-channel"))) {

                if(!plugin.config.getBoolean("enable-discord-commands")) {

                    if (!author.isBot()) {

                        BungeeUtils.sendPermissionMessage(plugin.minecraftLayout(msg, author), "staffchat.see");

                    }

                }

            }

        }

    }

}
