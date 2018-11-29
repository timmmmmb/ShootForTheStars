package game.menu;

import game.buttons.CreditsButton;
import game.buttons.ExitButton;
import game.buttons.OptionsButton;
import game.buttons.PlayButton;
import game.image.ImageLoader;
import game.main.LevelScene;
import game.settings.Settings;
import game.sound.SoundLoader;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

public class StartMenu extends VBox {
    private static StartMenu instance;
    private static MediaPlayer mediaPlayer;
    private StartMenu() {
        super();
    }


    public static StartMenu getInstance(){
        if(instance==null){
            //start the music
            mediaPlayer = new MediaPlayer(SoundLoader.getInstance().getMusic1());
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setVolume(Settings.musicVolume);
            mediaPlayer.play();
            instance = new StartMenu();
            createGUI();
        }
        return instance;
    }

    private static void createGUI(){
        instance.setMinSize(Settings.width,Settings.height);
        instance.setAlignment(Pos.CENTER);
        ImageView titleImageView = new ImageView(ImageLoader.getInstance().getTitleImage());
        PlayButton playButton = new PlayButton();
        CreditsButton creditsButton = new CreditsButton();
        OptionsButton optionsButton = new OptionsButton();
        ExitButton exitButton = new ExitButton();
        playButton.setOnAction(event -> {
            Settings.changeRoot(LevelScene.getInstance());
            LevelScene.restartLevel();
        });
        optionsButton.setOnAction(event -> Settings.changeRoot(Options.getInstance()));
        creditsButton.setOnAction(event -> Settings.changeRoot(Credits.getInstance()));
        exitButton.setOnAction(event -> System.exit(0));
        instance.getChildren().addAll(titleImageView,playButton,optionsButton,creditsButton,exitButton);
        instance.setStyle(Settings.menuStyle);
    }

    public void setVolume(double volume){
        mediaPlayer.setVolume(volume);
    }
}
