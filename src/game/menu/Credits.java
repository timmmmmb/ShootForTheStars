package game.menu;

import game.buttons.ExitButton;
import game.settings.Settings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

class Credits extends VBox {

    private static Credits instance;
    private Credits() {
        super();
    }


    static Credits getInstance(){
        if(instance==null){
            instance = new Credits();
            createGUI();
        }
        return instance;
    }

    private static VBox createGUI() {
        instance.setMinSize(Settings.width,Settings.height);
        instance.setAlignment(Pos.CENTER);
        Label credits = new Label("Programmer: Frey Tim\nGame Design: Frey Tim\nGraphics: Unlucky Studios");
        credits.setStyle(Settings.menuStyle);
        ExitButton exitButton = new ExitButton();
        exitButton.setOnAction(event ->  Settings.changeRoot(StartMenu.getInstance()));
        instance.setStyle(Settings.menuStyle);
        instance.getChildren().addAll(credits,exitButton);
        return instance;
    }
}
