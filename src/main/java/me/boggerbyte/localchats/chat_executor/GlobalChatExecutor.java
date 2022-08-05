package me.boggerbyte.localchats.chat_executor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GlobalChatExecutor extends ChatExecutor {
    public GlobalChatExecutor(String chatMessageLayout) {
        super(chatMessageLayout);
    }

    @Override
    public void onMessage(Player player, String message) {
        var chatMessage = chatMessageLayout
                .replace("%player%", player.getName())
                .replace("%message%", message);

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(chatMessage));
    }
}
