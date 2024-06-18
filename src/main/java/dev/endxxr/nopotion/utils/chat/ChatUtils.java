package dev.endxxr.nopotion.utils.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ChatUtils {

    public static Component colorize(String text, String... placeholders) {

        if (text == null || text.isBlank()) {
            return Component.empty();
        }

        for (int i = 0; i < placeholders.length; i += 2) {
            text = text.replace(placeholders[i], placeholders[i + 1]);
        }

        return MiniMessage.miniMessage().deserialize(text);
    }

}
