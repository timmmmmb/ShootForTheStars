package game.menu;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Credits extends Scene {

    private static final String menuStyle = "-fx-border-color: #000000; -fx-border-width: 5px;-fx-background-color:#000000;-fx-font-size: 24px;-fx-font-family:Segoe UI;fx-text-fill:#ffffff;";
    private static Credits instance;
    private Credits(Parent root, double width, double height) {
        super(root, width, height);
    }


    public static Credits getInstance(double width, double height, Stage gameStage){
        if(instance==null){
            instance = new Credits(createGUI(width, height, gameStage), width, height);
        }
        return instance;
    }

    private static VBox createGUI(double width, double height, Stage gameStage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Label credits = new Label("Programmer: Frey Tim\nGame Design: Frey Tim\nGraphics: Unlucky Studios");
        credits.setStyle(menuStyle);
        Image exitImageOnHover = new Image("Menu Screen/exit_buttons_pressed.png",height/5.0,height/5.0,true,false);
        Image exitImage = new Image("Menu Screen/exit_buttons.png", height/5.0,height/5.0,true,false);
        Button exitButton = new Button("", new ImageView(exitImage));
        exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> exitButton.setGraphic(new ImageView(exitImageOnHover)));
        exitButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> exitButton.setGraphic(new ImageView(exitImage)));
        exitButton.setOnAction(event -> gameStage.setScene(StartMenu.getInstance(width, height, gameStage)));
        exitButton.setStyle(menuStyle);
        root.setStyle(menuStyle);
        root.getChildren().addAll(credits,exitButton);
        return root;
    }
}
