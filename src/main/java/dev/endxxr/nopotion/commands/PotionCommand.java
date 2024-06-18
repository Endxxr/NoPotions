package dev.endxxr.nopotion.commands;

import dev.endxxr.nopotion.NoPotion;
import dev.endxxr.nopotion.commands.subcommands.BlacklistCommand;
import dev.endxxr.nopotion.commands.subcommands.ReloadSubCommand;
import dev.endxxr.nopotion.commands.subcommands.UnblacklistCommand;
import dev.endxxr.nopotion.utils.commands.CommandHandler;
import dev.endxxr.nopotion.utils.commands.SubCommand;

import java.util.Set;

public class PotionCommand extends CommandHandler {
    public PotionCommand(NoPotion instance) {
        super(instance);
    }

    @Override
    public Set<SubCommand> getSubCommands() {
        return Set.of(
                new BlacklistCommand(instance, "blacklist", "nopotion.blacklist", true),
                new UnblacklistCommand(instance, "unblacklist", "nopotion.blacklist", true),
                new ReloadSubCommand(instance, "reload", "nopotion.reload", true)
        );

    }
}
