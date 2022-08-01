package me.boggerbyte.localchats.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.boggerbyte.localchats.Main;
import me.boggerbyte.localchats.PlayerFinder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ChatListener implements Listener {
    private final Plugin plugin = Main.getInstance();

    private final PlayerFinder playerFinder;

    public ChatListener(PlayerFinder playerFinder) {
        this.playerFinder = playerFinder;
    }

    @EventHandler
    public void onChatMessage(AsyncChatEvent event) {
        event.setCancelled(true);

        var player = event.getPlayer();
        var message = event.message();

        var localMessage = Component.text()
                .append(Component.text("["))
                .append(Component.text("local", NamedTextColor.YELLOW))
                .append(Component.text("]"))
                .append(Component.space())
                .append(Component.text("<"))
                .append(Component.text(player.getName(), NamedTextColor.GRAY))
                .append(Component.text(">"))
                .append(Component.space())
                .append(message)
                .build();

        // running task in sync mode because event is asynchronous
        plugin.getServer().getScheduler().runTask(plugin, () -> playerFinder.findPlayersAround(player.getLocation())
                    .forEach(p -> p.sendMessage(localMessage)));
    }
}
