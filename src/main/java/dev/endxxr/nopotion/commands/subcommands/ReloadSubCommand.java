package dev.endxxr.nopotion.commands.subcommands;

import dev.endxxr.nopotion.NoPotion;
import dev.endxxr.nopotion.utils.chat.ChatUtils;
import dev.endxxr.nopotion.utils.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadSubCommand extends SubCommand {
    public ReloadSubCommand(NoPotion instance, String name, String permission, boolean consoleAllowed) {
        super(instance, name, permission, consoleAllowed);
    }

    @Override
    public void execute(CommandSender executor, String[] args) {
        instance.reload();
        executor.sendMessage(ChatUtils.colorize(instance.getConfig().getString("reloaded")));
    }

    @Override
    public List<String> tabComplete(CommandSender executor, String[] args) {
        return List.of();
    }
}
