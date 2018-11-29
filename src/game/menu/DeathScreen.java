package game.menu;

import game.buttons.ExitButton;
import game.buttons.PlayButton;
import game.main.LevelScene;
import game.settings.Settings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DeathScreen extends VBox {

    private static DeathScreen instance;

    private static int score = 0;
    private static int highscore = 0;
    private static  Label diedLabel = new Label("You died");
    private static Label scoreLabel = new Label("your Score: "+score);
    private static Label highscoreLabel = new Label("your Highscore: "+highscore);
    private static final String menuStyle = "-fx-border-color: #000000; -fx-border-width: 5px;-fx-background-color:#000000;-fx-font-size: 24px;-fx-font-family:Segoe UI;fx-text-fill:#ffffff;";
    private DeathScreen() {
        super();
    }

    public static DeathScreen getInstance(){
        if(instance==null){
            instance = new DeathScreen();
            createGUI();
        }
        return instance;
    }

    private static void createGUI() {
        instance.setMinSize(Settings.width,Settings.height);
        instance.setAlignment(Pos.CENTER);
        ExitButton exitButton = new ExitButton();
        exitButton.setOnAction(event ->
                Settings.changeRoot(StartMenu.getInstance())
        );
        PlayButton playButton = new PlayButton();
        playButton.setOnAction(event -> {
            Settings.changeRoot(LevelScene.getInstance());
            LevelScene.restartLevel();
        });
        HBox buttons = new HBox(playButton,exitButton);
        buttons.setAlignment(Pos.CENTER);
        playButton.setStyle(menuStyle);
        exitButton.setStyle(menuStyle);
        instance.setStyle(menuStyle);
        instance.getChildren().addAll(diedLabel,scoreLabel,highscoreLabel,buttons);
    }

    public void setScore(int score){
        DeathScreen.score = score;
        if(score > highscore){
            highscore = score;
        }
        scoreLabel.setText("your Score: "+score);
        highscoreLabel.setText("your Highscore: "+highscore);
    }
}
