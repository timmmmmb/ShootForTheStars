package game.menu;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class Options extends Scene {

    private static final String menuStyle = "-fx-border-color: #000000; -fx-border-width: 5px;-fx-background-color:#000000;";
    private static Options instance;
    private Options(Parent root, double width, double height) {
        super(root, width, height);
    }

    static Options getInstance(double width, double height, Stage gameStage){
        if(instance==null){
            instance = new Options(createGUI(width, height, gameStage), width, height);
        }
        return instance;
    }

    private static VBox createGUI(double width, double height, Stage gameStage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Image exitImageOnHover = new Image("Images/Menu Screen/exit_buttons_pressed.png",height/5.0,height/5.0,true,false);
        Image exitImage = new Image("Images/Menu Screen/exit_buttons.png", height/5.0,height/5.0,true,false);
        Button exitButton = new Button("", new ImageView(exitImage));
        exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> exitButton.setGraphic(new ImageView(exitImageOnHover)));
        exitButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> exitButton.setGraphic(new ImageView(exitImage)));
        exitButton.setOnAction(event -> gameStage.setScene(StartMenu.getInstance(width, height, gameStage)));
        exitButton.setStyle(menuStyle);
        root.setStyle(menuStyle);
        root.getChildren().addAll(exitButton);
        return root;
    }
}
