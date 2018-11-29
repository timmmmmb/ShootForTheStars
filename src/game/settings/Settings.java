package game.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Settings {
    public static Stage stage = null;
    private static final String resolution1920 = "1920x1080p";
    private static final String resolution1080 = "1080x720p";
    public static String resolution = resolution1920;
    public static ObservableList<String> resolutions =
            FXCollections.observableArrayList(
                    resolution1080,
                    resolution1920
            );
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

    public static void changeResolution(String newResolution){
        Settings.resolution = newResolution;
        Settings.width = Double.parseDouble(newResolution.substring(0,newResolution.indexOf("x")));
        Settings.height = Double.parseDouble(newResolution.substring(newResolution.indexOf("x")+1,newResolution.indexOf("p")));
    }
}
