package me.boggerbyte.localchats.chat_executor;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GlobalChatExecutor extends ChatExecutor {
    public GlobalChatExecutor(String chatMessageLayout) {
        super(chatMessageLayout);
    }

    @Override
    public void onMessage(Player player, Component message) {
        var chatMessage = MiniMessage.miniMessage().deserialize(chatMessageLayout,
                Placeholder.component("player", Component.text(player.getName())),
                Placeholder.component("message", message));

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(chatMessage));
    }
}
