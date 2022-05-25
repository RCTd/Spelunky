package GamePakage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    public Sound(){
        try{
            clip= AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new URL("file:rsc/Music/mCave.wav")));
        }catch(Exception e){
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }
    public void stop(){
        clip.stop();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void close(){
        clip.close();
    }
}
