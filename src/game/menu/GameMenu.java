package game.menu;

import game.buttons.ExitButton;
import game.buttons.PlayButton;
import game.settings.Settings;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class GameMenu extends VBox {
    private static GameMenu instance;
    private GameMenu(AnimationTimer gameTimer){
        this.setVisible(false);
        this.setAlignment(Pos.CENTER);
        PlayButton play = new PlayButton();
        play.setOnAction(event -> {
            gameTimer.start();
            this.setVisible(false);
        });
        this.getChildren().add(play);
        ExitButton exit = new ExitButton();
        exit.setOnAction(event -> {
                    Settings.changeRoot(StartMenu.getInstance());
                    this.setVisible(false);
                });
        this.getChildren().add(exit);
        this.setStyle(Settings.menuStyleTransparent);
        this.setHeight(exit.getHeight()+play.getHeight());
        this.setWidth(exit.getWidth());
        this.setLayoutX((Settings.width-this.getWidth())/2);
        this.setLayoutY((Settings.height-this.getHeight())/2);
    }

    public static GameMenu getInsctance(AnimationTimer gameTimer){
        if(instance==null){
            instance = new GameMenu(gameTimer);
        }
        return instance;
    }
}
