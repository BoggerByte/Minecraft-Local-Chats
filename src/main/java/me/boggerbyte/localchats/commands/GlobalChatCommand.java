package me.boggerbyte.localchats.commands;

import me.boggerbyte.localchats.chat_executor.GlobalChatExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GlobalChatCommand extends AbstractCommand {
    private final GlobalChatExecutor globalChatExecutor;

    public GlobalChatCommand(boolean isDisabled, GlobalChatExecutor globalChatExecutor) {
        super("local-chats.global", isDisabled);
        this.globalChatExecutor = globalChatExecutor;
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
        globalChatExecutor.onMessage(player, message);

        return true;
    }
}
