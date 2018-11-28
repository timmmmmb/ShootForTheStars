package game.buttons;

import game.settings.Settings;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OptionsButton extends Button {
    public OptionsButton(){
        Image exitImage = new Image("Images/Menu Screen/optionst_buttons.png", Settings.height /5.0,Settings.height/5.0,true,false);
        Image exitImageOnHover = new Image("Images/Menu Screen/optionst_buttons_pressed.png",Settings.height/5.0,Settings.height/5.0,true,false);
        this.setGraphic(new ImageView(exitImage));
        this.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> this.setGraphic(new ImageView(exitImageOnHover)));
        this.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> this.setGraphic(new ImageView(exitImage)));
        this.setStyle(Settings.menuStyle);
        this.setHeight(exitImage.getHeight());
        this.setWidth(exitImage.getWidth());
    }
}
