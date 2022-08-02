package me.boggerbyte.localchats;

import me.boggerbyte.localchats.chat_executor.GlobalChatExecutor;
import me.boggerbyte.localchats.chat_executor.LocalChatExecutor;
import me.boggerbyte.localchats.commands.GlobalChatCommand;
import me.boggerbyte.localchats.commands.LocalChatCommand;
import me.boggerbyte.localchats.listeners.ChatListener;
import me.boggerbyte.localchats.utils.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    @Override
    public void onLoad() {
        saveDefaultConfig();
        var config = getConfig();
        var configRequiredFields = new String[]{
                "local-chat-radius",
                "local-chat.layout",
                "local-chat.on-command",
                "local-chat.on-prefix",
                "local-chat.prefix",
                "global-chat.layout",
                "global-chat.on-command",
                "global-chat.on-prefix",
                "global-chat.prefix",
                "default-chat"
        };
        Arrays.stream(configRequiredFields)
                .filter(field -> !config.contains(field, true))
                .forEach(field -> Logger.log(Level.WARNING, "Missing required config field <" + field + ">. Using default value"));
    }

    @Override
    public void onEnable() {
        var config = getConfig();

        var localChatExecutor = new LocalChatExecutor(config.getString("local-chat.layout"), config.getDouble("local-chat-radius"));
        var globalChatExecutor = new GlobalChatExecutor(config.getString("global-chat.layout"));

        var chatListener = new ChatListener(
                localChatExecutor,
                globalChatExecutor,
                config.getBoolean("local-chat.on-prefix"),
                config.getBoolean("global-chat.on-prefix"),
                config.getString("local-chat.prefix"),
                config.getString("global-chat.prefix"),
                config.getString("default-chat"));
        getServer().getPluginManager().registerEvents(chatListener, this);

        if (config.getBoolean("local-chat.on-command")) {
            var localChatCommand = new LocalChatCommand(localChatExecutor);
            Objects.requireNonNull(getCommand("local")).setExecutor(localChatCommand);
        }
        if (config.getBoolean("global-chat.on-command")) {
            var globalChatCommand = new GlobalChatCommand(globalChatExecutor);
            Objects.requireNonNull(getCommand("global")).setExecutor(globalChatCommand);
        }
    }

    public static Plugin getInstance() {
        return Main.getPlugin(Main.class);
    }
}
