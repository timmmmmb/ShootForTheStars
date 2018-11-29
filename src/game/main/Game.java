package game.main;

import game.image.ImageLoader;
import game.menu.StartMenu;
import game.settings.Settings;
import game.sound.SoundLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage gameStage) {
        Settings.stage = gameStage;
        gameStage.setResizable(false);
        gameStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        gameStage.setMaximized(Settings.fullscreen);
        gameStage.setFullScreen(Settings.fullscreen);
        Settings.changeRoot(StartMenu.getInstance());
        Scene scene = new Scene(Settings.root,Settings.width,Settings.height);
        gameStage.setScene(scene);
        SoundLoader.getInstance();
        ImageLoader.getInstance();
        gameStage.show();
        gameStage.toFront();
    }
}
