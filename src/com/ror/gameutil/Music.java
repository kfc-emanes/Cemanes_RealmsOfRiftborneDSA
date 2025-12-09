package com.ror.gameutil;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.*;

public class Music {

    private static Clip clip;

    public static void playOgg(String path, boolean loop) {
        stop(); // Stop previous clip

        try {
            // Load OGG from classpath
            InputStream audioSrc = Music.class.getResourceAsStream(path);
            if (audioSrc == null) {
                System.out.println("OGG not found: " + path);
                return;
            }

            BufferedInputStream buffered = new BufferedInputStream(audioSrc);

            // Decode using VorbisSPI
            AudioInputStream ais = AudioSystem.getAudioInputStream(buffered);

            // Convert the OGG stream into playable PCM
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );

            AudioInputStream pcmStream = AudioSystem.getAudioInputStream(decodedFormat, ais);

            // Open audio clip
            clip = AudioSystem.getClip();
            clip.open(pcmStream);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (clip != null) {
            clip.stop();
            clip.flush();
            clip.close();
            clip = null;
        }
    }
}
