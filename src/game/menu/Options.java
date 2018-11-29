package game.menu;

import game.buttons.ExitButton;
import game.settings.Settings;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class Options extends VBox {
    private static Options instance;
    private Options() {
        super();
    }

    static Options getInstance(){
        if(instance==null){
            instance = new Options();
            createGUI();
        }
        return instance;
    }

    private static void createGUI() {
        instance.setMinSize(Settings.width,Settings.height);
        instance.setAlignment(Pos.CENTER);
        ExitButton exitButton = new ExitButton();
        exitButton.setOnAction(event -> Settings.changeRoot(StartMenu.getInstance()));
        instance.setStyle(Settings.menuStyle);
        instance.getChildren().addAll(exitButton);
    }
}
