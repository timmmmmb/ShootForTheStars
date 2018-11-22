package game.menu;

import game.main.LevelScene;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class StartMenu extends Scene {
    private static StartMenu instance;
    private static MediaPlayer mediaPlayer;
    private static final String menuStyle = "-fx-border-color: #000000; -fx-border-width: 5px;-fx-background-color:#000000;";
    private StartMenu(Parent root, double width, double height) {
        super(root, width, height);
    }


    public static StartMenu getInstance(double width, double height, Stage gameStage){
        if(instance==null){
            //start the music
            String musicFile = "resources/Sounds/Music/Music.mp3";

            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.play();
            instance = new StartMenu(createGUI(width, height, gameStage), width, height);
        }
        return instance;
    }

    private static VBox createGUI(double width, double height, Stage gameStage){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Image titleImage = new Image("Menu Screen/top_banner.jpg",height/2.5,height/2.5,true,false);
        ImageView titleImageView = new ImageView(titleImage);
        Image playImage = new Image("Menu Screen/play_buttons.png",height/5.0,height/5.0,true,false);
        Image playImageOnHover = new Image("Menu Screen/play_buttons_pressed_blue.png",height/5.0,height/5.0,true,false);
        Button playButton = new Button("", new ImageView(playImage));
        Image optionsImageOnHover = new Image("Menu Screen/optionst_buttons_pressed.png",height/5.0,height/5.0,true,false);
        Image optionsImage = new Image("Menu Screen/optionst_buttons.png", height/5.0,height/5.0,true,false);
        Button optionsButton = new Button("", new ImageView(optionsImage));
        Image creditsImageOnHover = new Image("Menu Screen/Creditst_buttons_pressed.png",height/5.0,height/5.0,true,false);
        Image creditsImage = new Image("Menu Screen/Creditst_buttons.png", height/5.0,height/5.0,true,false);
        Button creditsButton = new Button("", new ImageView(creditsImage));
        Image exitImageOnHover = new Image("Menu Screen/exit_buttons_pressed.png",height/5.0,height/5.0,true,false);
        Image exitImage = new Image("Menu Screen/exit_buttons.png", height/5.0,height/5.0,true,false);
        Button exitButton = new Button("", new ImageView(exitImage));
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> playButton.setGraphic(new ImageView(playImageOnHover)));
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> playButton.setGraphic(new ImageView(playImage)));
        playButton.setOnAction(event -> {
            gameStage.setScene(LevelScene.getInstance(width, height, gameStage));
            LevelScene.restartLevel();
        });
        playButton.setStyle(menuStyle);
        optionsButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> optionsButton.setGraphic(new ImageView(optionsImageOnHover)));
        optionsButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> optionsButton.setGraphic(new ImageView(optionsImage)));
        optionsButton.setOnAction(event -> gameStage.setScene(Options.getInstance(width, height, gameStage)));
        optionsButton.setStyle(menuStyle);
        creditsButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> creditsButton.setGraphic(new ImageView(creditsImageOnHover)));
        creditsButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> creditsButton.setGraphic(new ImageView(creditsImage)));
        creditsButton.setOnAction(event -> gameStage.setScene(Credits.getInstance(width, height, gameStage)));
        creditsButton.setStyle(menuStyle);
        exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> exitButton.setGraphic(new ImageView(exitImageOnHover)));
        exitButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> exitButton.setGraphic(new ImageView(exitImage)));
        exitButton.setOnAction(event -> System.exit(0));
        exitButton.setStyle(menuStyle);
        root.setStyle(menuStyle);
        root.getChildren().addAll(titleImageView,playButton,optionsButton,creditsButton,exitButton);
        return root;
    }

    public void setVolume(double volume){
        mediaPlayer.setVolume(volume);
    }
}
