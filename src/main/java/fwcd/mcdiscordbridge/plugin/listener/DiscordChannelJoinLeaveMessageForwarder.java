package fwcd.mcdiscordbridge.plugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fwcd.mcdiscordbridge.bot.registry.TextChannelRegistry;
import net.dv8tion.jda.api.JDA;

public class DiscordChannelJoinLeaveMessageForwarder implements Listener {
    private final JDA jda;
    private final TextChannelRegistry subscribedChannels;

    public DiscordChannelJoinLeaveMessageForwarder(JDA jda, TextChannelRegistry subscribedChannels) {
        this.jda = jda;
        this.subscribedChannels = subscribedChannels;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        subscribedChannels.broadcastMessage(stripColorCodes(event.getJoinMessage()), jda);
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        subscribedChannels.broadcastMessage(stripColorCodes(event.getQuitMessage()), jda);
    }
    
    private String stripColorCodes(String message) {
        System.out.println(message);
        return message.replaceAll("§[a-fA-F0-9]", "");
    }
}
