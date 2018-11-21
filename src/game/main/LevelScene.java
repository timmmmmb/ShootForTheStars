package game.main;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LevelScene extends Scene {

    private static final String menuStyle = "-fx-border-color: #000000; -fx-border-width: 5px;-fx-background-color:#000000;-fx-font-size: 24px;-fx-font-family:Segoe UI;fx-text-fill:#ffffff;";
    private static LevelScene instance;
    public LevelScene(Parent root, double width, double height) {
        super(root, width, height);
    }


    public static LevelScene getInstance(double width, double height, Stage gameStage){
        if(instance==null){
            instance = new LevelScene(createGUI(width, height, gameStage), width, height);
        }
        return instance;
    }

    private static VBox createGUI(double width, double height, Stage gameStage) {
        VBox root = new VBox();
        Image backgroundImage = new Image("Background/background.jpg",width,width,true,false);
        ImageView backgroundImageView = new ImageView(backgroundImage);
        root.setAlignment(Pos.CENTER);
        root.setStyle(menuStyle);
        root.getChildren().addAll(backgroundImageView);
        return root;
    }
}
