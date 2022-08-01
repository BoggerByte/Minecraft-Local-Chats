package me.boggerbyte.localchats.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShoutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (args.length == 0) return false;
        if (!(sender instanceof Player player)) return false;

        var message = Component.text(String.join(" ", args));
        var globalMessage = Component.text()
                .append(Component.text("["))
                .append(Component.text("global", NamedTextColor.YELLOW))
                .append(Component.text("]"))
                .append(Component.space())
                .append(Component.text("<"))
                .append(Component.text(player.getName(), NamedTextColor.GRAY))
                .append(Component.text(">"))
                .append(Component.space())
                .append(message)
                .build();
        player.getWorld().getPlayers().forEach(p -> p.sendMessage(globalMessage));

        return true;
    }
}
