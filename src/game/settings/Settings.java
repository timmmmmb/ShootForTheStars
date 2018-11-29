package game.settings;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Settings {
    public static Stage stage = null;
    public static double width = 1920;
    public static double height = 1080;
    public static double musicVolume = 50;
    public static double effectVolume = 50;
    public static final String menuStyle = "-fx-border-color: #000000; -fx-border-width: 5px;-fx-background-color:#000000;-fx-font-size: 24px;-fx-font-family:Segoe UI;fx-text-fill:#ffffff;";
    public static final String menuStyleTransparent = "-fx-border-color: transparent; -fx-border-width: 5px;-fx-background-color:transparent;";
    public static boolean fullscreen = true;
    public static int highscore = 0;
    public static AnchorPane root = new AnchorPane();

    public static void changeRoot(Parent parent){
        root.getChildren().clear();
        root.getChildren().add(parent);
    }

    /**
     * loads all the settings from a .ini file
     */
    public static void loadSettings(){

    }
}
