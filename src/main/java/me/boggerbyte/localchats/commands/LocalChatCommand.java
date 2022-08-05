package me.boggerbyte.localchats.commands;

import me.boggerbyte.localchats.chat_executor.LocalChatExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LocalChatCommand extends AbstractCommand {
    private final LocalChatExecutor localChatExecutor;

    public LocalChatCommand(boolean isDisabled, LocalChatExecutor localChatExecutor) {
        super("local-chats.local", isDisabled);
        this.localChatExecutor = localChatExecutor;
    }


    public boolean execute(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!(sender instanceof Player player)) return false;
        if (args.length == 0) return false;

        var message = String.join(" ", args);
        localChatExecutor.onMessage(player, message);

        return true;
    }
}
