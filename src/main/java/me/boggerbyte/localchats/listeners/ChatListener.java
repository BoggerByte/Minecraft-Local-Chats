package me.boggerbyte.localchats.listeners;

import me.boggerbyte.localchats.Main;
import me.boggerbyte.localchats.chat_executor.ChatExecutor;
import me.boggerbyte.localchats.chat_executor.GlobalChatExecutor;
import me.boggerbyte.localchats.chat_executor.LocalChatExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class ChatListener implements Listener {
    private final Plugin plugin = Main.getInstance();

    private final LocalChatExecutor localChatExecutor;
    private final GlobalChatExecutor globalChatExecutor;
    private final boolean localChatOnPrefix;
    private final boolean globalChatOnPrefix;
    private final String localChatPrefix;
    private final String globalChatPrefix;
    private final String defaultChat;

    public ChatListener(
            LocalChatExecutor localChatExecutor,
            GlobalChatExecutor globalChatExecutor,
            boolean localChatOnPrefix,
            boolean globalChatOnPrefix,
            String localChatPrefix,
            String globalChatPrefix,
            String defaultChat
    ) {
        this.localChatExecutor = localChatExecutor;
        this.globalChatExecutor = globalChatExecutor;
        this.localChatOnPrefix = localChatOnPrefix;
        this.globalChatOnPrefix = globalChatOnPrefix;
        this.localChatPrefix = localChatPrefix;
        this.globalChatPrefix = globalChatPrefix;
        this.defaultChat = defaultChat;
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        var player = event.getPlayer();
        var message = event.getMessage();

        ChatExecutor chatExecutor = null;
        if (defaultChat.equals("local") || localChatOnPrefix && message.startsWith(localChatPrefix)) {
            if (!player.hasPermission("local-chats.local")) return;
            chatExecutor = localChatExecutor;
            message = message.substring(localChatPrefix.length());
        }
        if (defaultChat.equals("global") || globalChatOnPrefix && message.startsWith(globalChatPrefix)) {
            if (!player.hasPermission("local-chats.global")) return;
            chatExecutor = globalChatExecutor;
            message = message.substring(globalChatPrefix.length());
        }

        var finalChatExecutor = chatExecutor;
        var finalMessage = message;
        // running task in sync mode because event is asynchronous
        if (chatExecutor != null) plugin.getServer().getScheduler().runTask(plugin,
                () -> finalChatExecutor.onMessage(player, finalMessage));
    }
}
