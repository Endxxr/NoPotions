package dev.endxxr.nopotion;

import dev.endxxr.nopotion.commands.PotionCommand;
import dev.endxxr.nopotion.listeners.PotionInteractListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class NoPotion extends JavaPlugin {

    private YamlConfiguration blackListedFile;
    private Set<UUID> blacklistedPlayers;
    private final File BLACKLISTED_FILE_PATH = new File(getDataFolder(), "blacklisted.yml");

    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("=== NoPotion ===");
        getLogger().info("Author: Endxxr");
        getLogger().info("Version: "+getDescription().getVersion());
        getLogger().info("Loading...");
        saveConfigs();
        addPlayersFromConfig();
        setCommands();
        setListeners();
        getLogger().info("Loaded!");
        getLogger().info("=== NoPotion ===");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabling...");
    }

    private void setListeners() {
        getServer().getPluginManager().registerEvents(new PotionInteractListener(this), this);
    }

    private void saveConfigs() {
        saveDefaultConfig();
        saveResource("blacklisted.yml", false);
        blackListedFile = YamlConfiguration.loadConfiguration(BLACKLISTED_FILE_PATH);
    }

    private void setCommands() {
        PluginCommand potionCommand = getCommand("potion");
        if (potionCommand == null) {
            getLogger().severe("Command not found!");
            return;
        }

        potionCommand.setExecutor(new PotionCommand(this));
        potionCommand.setTabCompleter(new PotionCommand(this));
    }

    public void reload() {

        reloadConfig();
        blackListedFile = YamlConfiguration.loadConfiguration(BLACKLISTED_FILE_PATH);
        addPlayersFromConfig();
        getLogger().info("Config reloaded!");


    }

    private void addPlayersFromConfig() {
        blacklistedPlayers = new HashSet<>();
        List<String> uuidStringList = blackListedFile.getStringList("blacklisted");
        for (String uuidString : uuidStringList) {
            blacklistedPlayers.add(UUID.fromString(uuidString));
        }
    }

    public void setAndSave() {

        try {

            List<String> uuidStringList = blacklistedPlayers.stream().map(UUID::toString).toList();

            blackListedFile.set("blacklisted", uuidStringList);
            blackListedFile.save(BLACKLISTED_FILE_PATH);
        } catch (IOException e) {
            getLogger().severe(e.getMessage());
        }


    }



    public boolean addBlackListedPlayer(Player player) {

        if (blacklistedPlayers.contains(player.getUniqueId())) {
            getLogger().info(player.getUniqueId()+" is already blacklisted!");
            return false;
        }

        blacklistedPlayers.add(player.getUniqueId());
        setAndSave();

        return true;
    }

    public boolean removeBlackListedPlayer(Player player) {

        if (!blacklistedPlayers.contains(player.getUniqueId())) {
            getLogger().info(player.getUniqueId()+" isn't blacklisted!");
            return false;
        }
        blacklistedPlayers.remove(player.getUniqueId());
        setAndSave();

        return true;
    }

    public boolean isBlacklisted(Player player) {
        return blacklistedPlayers.contains(player.getUniqueId());
    }

}
