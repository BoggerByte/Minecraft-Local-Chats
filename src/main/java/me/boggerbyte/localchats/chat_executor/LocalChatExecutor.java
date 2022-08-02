package me.boggerbyte.localchats.chat_executor;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

public class LocalChatExecutor extends ChatExecutor {
    private final double radius;

    public LocalChatExecutor(String chatMessageLayout, double radius) {
        super(chatMessageLayout);
        this.radius = radius;
    }

    @Override
    public void onMessage(Player player, Component message) {
        var chatMessage = MiniMessage.miniMessage().deserialize(chatMessageLayout,
                Placeholder.component("player", Component.text(player.getName())),
                Placeholder.component("message", message));

        player.sendMessage(chatMessage);
        player.getNearbyEntities(radius, radius, radius).stream()
                .filter(e -> e instanceof Player)
                .forEach(p -> p.sendMessage(chatMessage));
    }
}
