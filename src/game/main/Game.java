package game.main;

import game.image.ImageLoader;
import game.menu.StartMenu;
import game.settings.Settings;
import game.sound.SoundLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Game extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage gameStage) {
        Settings.loadSettings();
        Settings.writeSettings();
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

    public static void restartApplication()
    {
        try {
            Settings.writeSettings();
            final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            final File currentJar = new File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI());

            /* is it a jar file? */
            if(!currentJar.getName().endsWith(".jar"))
                return;

            /* Build command: java -jar application.jar */
            final ArrayList<String> command = new ArrayList<>();
            command.add(javaBin);
            command.add("-jar");
            command.add(currentJar.getPath());

            final ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
            System.exit(0);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
