package game.main;

import game.menu.StartMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage gameStage) {
        gameStage.setScene(StartMenu.getInstance(1080,720, gameStage));
        gameStage.show();
    }
}
