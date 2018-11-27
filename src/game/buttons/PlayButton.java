package game.buttons;

import game.settings.Settings;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class PlayButton extends Button {
    public PlayButton(){
        Image playImage = new javafx.scene.image.Image("Images/Menu Screen/play_buttons.png", Settings.height /5.0,Settings.height/5.0,true,false);
        Image playImageOnHover = new javafx.scene.image.Image("Images/Menu Screen/play_buttons_pressed_blue.png",Settings.height/5.0,Settings.height/5.0,true,false);
        this.setGraphic(new ImageView(playImage));
        this.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> this.setGraphic(new ImageView(playImageOnHover)));
        this.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> this.setGraphic(new ImageView(playImage)));
        this.setStyle(Settings.menuStyle);
        this.setHeight(playImage.getHeight());
        this.setWidth(playImage.getWidth());
    }
}
