package game.main;

import game.image.ImageLoader;
import game.menu.StartMenu;
import game.sound.SoundLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage gameStage) {
        gameStage.setScene(StartMenu.getInstance(1080,720, gameStage));
        SoundLoader.getInstance();
        ImageLoader.getInstance();
        gameStage.show();
    }
}
