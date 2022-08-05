package me.boggerbyte.localchats.chat_executor;

import org.bukkit.entity.Player;

public abstract class ChatExecutor {
    public final String chatMessageLayout;

    protected ChatExecutor(String chatMessageLayout) {
        this.chatMessageLayout = chatMessageLayout;
    }

    public abstract void onMessage(Player player, String message);
}
