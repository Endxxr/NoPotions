package dev.endxxr.nopotion.utils.commands;

import dev.endxxr.nopotion.NoPotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class SubCommand {

    protected final NoPotion instance;
    private final String name;
    private final String permission;
    private final boolean consoleAllowed;

    public abstract void execute(CommandSender executor, String[] args);
    public abstract List<String> tabComplete(CommandSender executor, String[] args);
}
