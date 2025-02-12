package Principal;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Audio() {
        soundURL[0] = getClass().getResource("/Audio/sons/armas/cabra_vush.wav");
        soundURL[1] = getClass().getResource("/Audio/sons/armas/pickup.wav");
        soundURL[2] = getClass().getResource("/Audio/sons/personagens/passo1.wav");
        soundURL[3] = getClass().getResource("/Audio/sons/personagens/passo2.wav");
        soundURL[4] = getClass().getResource("/Audio/sons/personagens/passo3.wav");
        soundURL[5] = getClass().getResource("/Audio/sons/personagens/passo4.wav");
    }

    // inciar o arquivo de audio
    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tocar o arquivo de audio
    public void play() {
        clip.start();
    }

    // loop do arquivo de audio
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // parar o arquivo de audio
    public void stop() {
        clip.stop();
    }
}
