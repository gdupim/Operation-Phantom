package Principal;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
        soundURL[6] = getClass().getResource("/Audio/msc/ambient.wav");
        soundURL[7] = getClass().getResource("/Audio/sons/personagens/drdrHit.wav");
        soundURL[8] = getClass().getResource("/Audio/sons/personagens/snakeHit.wav");
        soundURL[9] = getClass().getResource("/Audio/sons/personagens/drdrHit.wav");
        soundURL[10] = getClass().getResource("/Audio/sons/personagens/tempHit.wav");
        soundURL[11] = getClass().getResource("/Audio/sons/personagens/abrirInv.wav");
        soundURL[12] = getClass().getResource("/Audio/sons/personagens/fecharInv.wav");
    }

    // inciar o arquivo de audio
    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.err.println("Error loading audio file: " + e.getMessage());
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
