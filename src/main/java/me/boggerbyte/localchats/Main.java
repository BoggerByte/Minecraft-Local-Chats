package me.boggerbyte.localchats;

import me.boggerbyte.localchats.commands.ShoutCommand;
import me.boggerbyte.localchats.listeners.ChatListener;
import me.boggerbyte.localchats.utils.Logger;
import org.bukkit.entity.Player;
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
                "max-x-distance",
                "max-y-distance",
                "max-z-distance",
        };
        Arrays.stream(configRequiredFields)
                .filter(field -> !config.contains(field, true))
                .forEach(field -> Logger.log(Level.WARNING, "Missing required config field <" + field + ">. Using default value"));
    }

    @Override
    public void onEnable() {
        var config = getConfig();

        PlayerFinder playerFinder = location -> {
            var nearByEntities = location.getNearbyEntities(
                        config.getDouble("max-x-distance"),
                        config.getDouble("max-y-distance"),
                        config.getDouble("max-z-distance"));
            return nearByEntities.stream()
                .filter(e -> e instanceof Player)
                .map(e -> (Player) e)
                .toList();
        };
        getServer().getPluginManager().registerEvents(new ChatListener(playerFinder), this);

        Objects.requireNonNull(getCommand("shout")).setExecutor(new ShoutCommand());
    }

    public static Plugin getInstance() {
        return Main.getPlugin(Main.class);
    }
}
