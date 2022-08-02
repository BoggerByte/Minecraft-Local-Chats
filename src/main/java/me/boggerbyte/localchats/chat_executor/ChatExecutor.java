package me.boggerbyte.localchats.chat_executor;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public abstract class ChatExecutor {
    public final String chatMessageLayout;

    protected ChatExecutor(String chatMessageLayout) {
        this.chatMessageLayout = chatMessageLayout;
    }

    public abstract void onMessage(Player player, Component message);
}
