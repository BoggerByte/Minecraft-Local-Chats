package me.boggerbyte.localchats.commands;

import me.boggerbyte.localchats.chat_executor.LocalChatExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LocalChatCommand implements CommandExecutor {
    private final LocalChatExecutor localChatExecutor;

    public LocalChatCommand(LocalChatExecutor localChatExecutor) {
        this.localChatExecutor = localChatExecutor;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (args.length == 0) return false;
        if (!(sender instanceof Player player)) return false;

        var message = String.join(" ", args);
        localChatExecutor.onMessage(player, message);

        return true;
    }
}
