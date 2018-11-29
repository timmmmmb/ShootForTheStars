package game.menu;

import game.buttons.ExitButton;
import game.settings.Settings;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

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
        //add dropdown for resolution

        //add slider for Sound Volume

        //add slider for Music Volume

        ExitButton exitButton = new ExitButton();
        exitButton.setOnAction(event -> Settings.changeRoot(StartMenu.getInstance()));
        instance.setStyle(Settings.menuStyle);
        instance.getChildren().addAll(exitButton);
    }
}
