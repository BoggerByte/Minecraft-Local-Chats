package me.boggerbyte.localchats.commands;

import me.boggerbyte.localchats.chat_executor.GlobalChatExecutor;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GlobalChatCommand implements CommandExecutor {

    private final GlobalChatExecutor globalChatExecutor;

    public GlobalChatCommand(GlobalChatExecutor globalChatExecutor) {
        this.globalChatExecutor = globalChatExecutor;
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

        var message = Component.text(String.join(" ", args));
        globalChatExecutor.onMessage(player, message);

        return true;
    }
}
