package dev.endxxr.nopotion.commands.subcommands;

import dev.endxxr.nopotion.NoPotion;
import dev.endxxr.nopotion.utils.chat.ChatUtils;
import dev.endxxr.nopotion.utils.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BlacklistCommand extends SubCommand {
    public BlacklistCommand(NoPotion instance, String name, String permission, boolean consoleAllowed) {
        super(instance, name, permission, consoleAllowed);
    }

    @Override
    public void execute(CommandSender executor, String[] args) {

        if (args.length<2) {
            executor.sendMessage(ChatUtils.colorize(instance.getConfig().getString("specify-a-player")));
            return;
        }


        String playerName = args[1];
        Player player = Bukkit.getPlayer(playerName);

        if (player == null) {
            executor.sendMessage(ChatUtils.colorize(instance.getConfig().getString("specify-a-player")));
            return;
        }

        boolean result = instance.addBlackListedPlayer(player);

        if (!result) {
            executor.sendMessage(ChatUtils.colorize(instance.getConfig().getString("already-blacklisted")));
            return;
        }
        executor.sendMessage(ChatUtils.colorize(instance.getConfig().getString("blacklisted"), "%player%", player.getName()));


    }

    @Override
    public List<String> tabComplete(CommandSender executor, String[] args) {
        if (args.length==2) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        }

        return null;
    }
}
