package game.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

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
        try (Scanner scanner = new Scanner(new File("settings.txt"))) {
            while (scanner.hasNext()){
                String line = scanner.next();
                if(line.contains("resolution")){
                    changeResolution(line.substring(line.indexOf("=")+1));
                }else if(line.contains("musicVolume")){
                    musicVolume = Double.parseDouble(line.substring(line.indexOf("=")+1));
                }else if(line.contains("soundVolume")){
                    effectVolume = Double.parseDouble(line.substring(line.indexOf("=")+1));
                }else if(line.contains("fullscreen")){
                    fullscreen = Boolean.parseBoolean(line.substring(line.indexOf("=")+1));
                }else if(line.contains("highscore")){
                    highscore = Integer.parseInt(line.substring(line.indexOf("=")+1));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeSettings(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("settings.txt", "UTF-8");
            writer.println("resolution="+resolution);
            writer.println("musicVolume="+musicVolume);
            writer.println("soundVolume="+effectVolume);
            writer.println("fullscreen="+fullscreen);
            writer.println("highscore="+highscore);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void changeResolution(String newResolution){
        if(!newResolution.contains("x")||!newResolution.contains("p"))return;
        Settings.resolution = newResolution;
        Settings.width = Double.parseDouble(newResolution.substring(0,newResolution.indexOf("x")));
        Settings.height = Double.parseDouble(newResolution.substring(newResolution.indexOf("x")+1,newResolution.indexOf("p")));
    }
}
