package game.sound;

import game.main.Game;
import javafx.scene.media.Media;

import java.net.URISyntaxException;
import java.util.Objects;

public class SoundLoader {
    private static SoundLoader instance;
    private Media music1;

    private SoundLoader(){
        initializeImages();
    }

    /**
     * loads all the Sounds
     */
    private void initializeImages() {
        try {
            music1 = new Media(Objects.requireNonNull(Game.class.getClassLoader().getResource("Sounds/Music/Music.mp3")).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static SoundLoader getInstance(){
        if(instance==null){
            instance = new SoundLoader();
        }
        return instance;
    }

    public Media getMusic1() {
        return music1;
    }
}
