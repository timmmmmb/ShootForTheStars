package game.menu;

import game.main.LevelScene;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeathScreen extends Scene {

    private static DeathScreen instance;

    private static int score = 0;
    private static int highscore = 0;
    private static  Label diedLabel = new Label("You died");
    private static Label scoreLabel = new Label("your Score: "+score);
    private static Label highscoreLabel = new Label("your Highscore: "+highscore);
    private static final String menuStyle = "-fx-border-color: #000000; -fx-border-width: 5px;-fx-background-color:#000000;-fx-font-size: 24px;-fx-font-family:Segoe UI;fx-text-fill:#ffffff;";
    private DeathScreen(Parent root, double width, double height) {
        super(root, width, height);
    }

    public static DeathScreen getInstance(double width, double height, Stage gameStage){
        if(instance==null){
            instance = new DeathScreen(createGUI(width, height, gameStage), width, height);
        }
        return instance;
    }

    private static VBox createGUI(double width, double height, Stage gameStage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Image exitImageOnHover = new Image("Images/Menu Screen/exit_buttons_pressed.png",height/5.0,height/5.0,true,false);
        Image exitImage = new Image("Images/Menu Screen/exit_buttons.png", height/5.0,height/5.0,true,false);
        Button exitButton = new Button("", new ImageView(exitImage));
        exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> exitButton.setGraphic(new ImageView(exitImageOnHover)));
        exitButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> exitButton.setGraphic(new ImageView(exitImage)));
        exitButton.setOnAction(event -> {
            gameStage.setScene(StartMenu.getInstance());
            System.out.println(gameStage.getScene());
        });
        Image playImage = new Image("Images/Menu Screen/play_buttons.png",height/5.0,height/5.0,true,false);
        Image playImageOnHover = new Image("Images/Menu Screen/play_buttons_pressed_blue.png",height/5.0,height/5.0,true,false);
        Button playButton = new Button("", new ImageView(playImage));
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> playButton.setGraphic(new ImageView(playImageOnHover)));
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> playButton.setGraphic(new ImageView(playImage)));
        playButton.setOnAction(event -> {
            gameStage.setScene(LevelScene.getInstance(width, height, gameStage));
            LevelScene.restartLevel();
        });
        HBox buttons = new HBox(playButton,exitButton);
        buttons.setAlignment(Pos.CENTER);
        playButton.setStyle(menuStyle);
        exitButton.setStyle(menuStyle);
        root.setStyle(menuStyle);
        root.getChildren().addAll(diedLabel,scoreLabel,highscoreLabel,buttons);
        return root;
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
