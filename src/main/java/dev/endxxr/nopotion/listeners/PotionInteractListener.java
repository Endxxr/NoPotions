package dev.endxxr.nopotion.listeners;

import dev.endxxr.nopotion.NoPotion;
import dev.endxxr.nopotion.utils.chat.ChatUtils;
import dev.endxxr.nopotion.utils.serializers.SoundSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PotionInteractListener implements Listener {

    private final NoPotion instance;

    public PotionInteractListener(NoPotion instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPotionUse(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack itemInHand =  player.getInventory().getItemInMainHand();

        if (!itemInHand.getType().name().toLowerCase().endsWith("potion")) {
            return;
        }

        if (!instance.isBlacklisted(player)) {
            return;
        }

        event.setCancelled(true);
        player.sendMessage(ChatUtils.colorize(instance.getConfig().getString("cant-use")));
        player.playSound(SoundSerializer.deserialize(instance.getConfig().getString("cant-use-sound")));


    }

}
