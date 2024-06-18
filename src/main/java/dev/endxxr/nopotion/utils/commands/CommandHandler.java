package dev.endxxr.nopotion.utils.commands;


import dev.endxxr.nopotion.NoPotion;
import dev.endxxr.nopotion.utils.chat.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class CommandHandler implements TabExecutor, CommandExecutor {

    protected final NoPotion instance;
    protected final Map<String, SubCommand> subCommands = new HashMap<>();

    public CommandHandler(NoPotion instance) {
        this.instance = instance;
        registerSubCommands();
    }

    public void registerSubCommands() {
        getSubCommands().forEach(subCommand -> subCommands.put(subCommand.getName(), subCommand));
    }

    public abstract Set<SubCommand> getSubCommands();

    public boolean handleCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            commandSender.sendMessage(ChatUtils.colorize(instance.getConfig().getString("command-usage")));
            return true;
        }

        if (subCommands.containsKey(strings[0])) {

            SubCommand subCommand = subCommands.get(strings[0]);

            if (!commandSender.hasPermission(subCommand.getPermission())) {
                commandSender.sendMessage(ChatUtils.colorize(instance.getConfig().getString("no-permission")));
                return true;
            }


            subCommand.execute(commandSender, strings);
            return true;
        }

        commandSender.sendMessage(ChatUtils.colorize(instance.getConfig().getString("command-usage")));
        return true;
    }

    public List<String> handleTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {


        if (strings.length == 1) {
            List<String> subCommands = new ArrayList<>(this.subCommands.keySet());
            subCommands.removeIf(subCommand -> !subCommand.startsWith(strings[0]));
            return subCommands;
        }

        if (strings.length >= 2) {
            if (subCommands.containsKey(strings[0])) {
                return subCommands.get(strings[0]).tabComplete(commandSender, strings);
            }
        }

        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return handleCommand(sender, command, label, args);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return handleTabComplete(sender, command, alias, args);
    }
}
