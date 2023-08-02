package Sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip clip;
    URL soundURL[];
    public Sound(){
        soundURL= new URL[10];
        soundURL[0] = getClass().getResource("/Sound/base.wav");
        soundURL[1] = getClass().getResource("/Sound/running.wav");
        soundURL[2] = getClass().getResource("/Sound/coin.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
        
    }
}
