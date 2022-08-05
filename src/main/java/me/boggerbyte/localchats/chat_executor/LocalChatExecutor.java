package me.boggerbyte.localchats.chat_executor;

import org.bukkit.entity.Player;

public class LocalChatExecutor extends ChatExecutor {
    private final double radius;

    public LocalChatExecutor(String chatMessageLayout, double radius) {
        super(chatMessageLayout);
        this.radius = radius;
    }

    @Override
    public void onMessage(Player player, String message) {
        var chatMessage = chatMessageLayout
                .replace("%player%", player.getName())
                .replace("%message%", message);

        player.sendMessage(chatMessage);
        player.getNearbyEntities(radius, radius, radius).stream()
                .filter(e -> e instanceof Player)
                .forEach(p -> p.sendMessage(chatMessage));
    }
}
