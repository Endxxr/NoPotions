package dev.endxxr.nopotion.utils.serializers;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;

public class SoundSerializer {

    public static Sound deserialize(String serialized) {

        try {
            String[] soundSplit = serialized.split(";");

            if (soundSplit.length < 3) {
                throw new IllegalStateException("Incorrect config");
            }

            String keyString = soundSplit[0];
            String volumeString = soundSplit[1];
            String pitchString = soundSplit[2];

            Key key = Key.key(keyString);
            float volume = Float.parseFloat(volumeString);
            float pitch = Float.parseFloat(pitchString);

            return Sound.sound(key, Sound.Source.AMBIENT, volume, pitch);


        } catch (Exception e) {
            e.printStackTrace();
            return Sound.sound(Key.key("minecraft:entity.villager.no"), Sound.Source.AMBIENT, 1, 1);
        }





    }


}
