package me.boggerbyte.localchats.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCommand implements CommandExecutor {
    private final String permission;
    private final boolean isDisabled;

    protected AbstractCommand(String permission, boolean isDisabled) {
        this.permission = permission;
        this.isDisabled = isDisabled;
    }

    public abstract boolean execute(@NotNull CommandSender sender,
                                    @NotNull Command command,
                                    @NotNull String label,
                                    @NotNull String[] args);

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!sender.hasPermission(permission)) return true;
        if (isDisabled) return true;

        return execute(sender, command, label, args);
    }

}
