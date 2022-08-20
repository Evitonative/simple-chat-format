package com.github.evitonative.simplechatformat;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerChatPreviewEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SimpleChatFormat extends JavaPlugin implements Listener {
    private Chat chat;

    @Override
    public void onEnable() {
        this.setupChat();

        getServer().getPluginManager().registerEvents(this, this);

        saveDefaultConfig();
    }

    private void setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        assert rsp != null;
        chat = rsp.getProvider();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        chat = null;
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event){
        String message = formatMessage(event.getPlayer(), event.getMessage());
        event.setFormat(message);
    }

    @EventHandler
    public void onAsyncPlayerChatPreviewEvent(@SuppressWarnings("deprecation") AsyncPlayerChatPreviewEvent event){
        String message = formatMessage(event.getPlayer(), event.getMessage());
        event.setFormat(message);
    }


    private String formatMessage(Player player, String text){
        String message = formatMessage(Objects.requireNonNull(getConfig().getString("layout")));

        message = message
                .replace("{prefix}", formatMessage(chat.getPlayerPrefix(player)))
                .replace("{suffix}", formatMessage(chat.getPlayerSuffix(player)))
                .replace("{displayname}", player.getDisplayName())
                .replace("{username}", player.getName())
                .replace("{messsage}", player.hasPermission("scm.inMessage")?formatMessage(text):text);


        return message;
    }

    private String formatMessage(String format){
        if(format.equals("")) return "";

        format = format
                .replace("&0", "§0")
                .replace("&1", "§1")
                .replace("&2", "§2")
                .replace("&3", "§3")
                .replace("&4", "§4")
                .replace("&5", "§5")
                .replace("&6", "§6")
                .replace("&7", "§7")
                .replace("&8", "§8")
                .replace("&9", "§9")
                .replace("&a", "§a")
                .replace("&b", "§b")
                .replace("&c", "§c")
                .replace("&d", "§d")
                .replace("&e", "§e")
                .replace("&f", "§f")
                .replace("&k", "§k")
                .replace("&l", "§l")
                .replace("&m", "§m")
                .replace("&n", "§n")
                .replace("&o", "§o")
                .replace("&r", "§r");

        String[] prefixHexParts = format.split("(?=&#[0-f]{6})");

        StringBuilder message = new StringBuilder();

        for (String part : prefixHexParts){
            if(!part.matches("^(?=&#[0-f]{6}).*$")) {
                message.append(part);
                continue;
            }

            part = part.substring(1);

            String hex = part.substring(0,7);
            part = part.substring(7);

            message.append(ChatColor.of(hex).toString()).append(part);
        }

        return  message.toString();
    }
}
