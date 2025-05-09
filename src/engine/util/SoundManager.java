package engine.util;

import engine.Configuration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class SoundManager {
    private static final HashMap<String, String> sounds = new HashMap<String, String>();

    // load sound files from path specified in Configuration
    static {
        File soundDir = new File(Configuration.PATH_SOUNDS);
        if (soundDir.exists() && soundDir.isDirectory()) {
            for (File file : Objects.requireNonNull(soundDir.listFiles())) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".wav")) {
                    String fileName = file.getName();
                    String key = fileName.substring(0, fileName.lastIndexOf('.')); // without extension

                    if (sounds.containsKey(key)) {
                        throw new RuntimeException("Duplicate sound key detected: \"" + key + "\" from file " + file.getPath());
                    }

                    sounds.put(key, file.getPath());
                }
            }
        } else {
            System.err.println("Audio directory not found: " + Configuration.PATH_SOUNDS);
        }
    }

    public static void play(String soundName) {
        if (!sounds.containsKey(soundName)) {
            throw new RuntimeException("Key does not exist.");
        }

        File file = new File(sounds.get(soundName));
        if (!file.exists()) {
            throw new RuntimeException("Sound file does not exist.");
        }

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);

            clip.start();

            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
