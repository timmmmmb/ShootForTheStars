package game.menu;

import game.buttons.ExitButton;
import game.settings.Settings;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
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
        Label resolutionLabel = new Label("Resolution: ");
        final ComboBox<String> resolution = new ComboBox<>(Settings.resolutions);
        resolution.getSelectionModel().select(Settings.resolution);
        resolution.valueProperty().addListener((ov, t, newResolution) -> Settings.changeResolution(newResolution));
        CheckBox fullscreen = new CheckBox("Fullscreen");
        fullscreen.selectedProperty().addListener((ov, old_val, new_val) -> Settings.fullscreen = new_val);
        HBox resolutionBox = new HBox(resolutionLabel,resolution,fullscreen);
        resolutionBox.setAlignment(Pos.CENTER);
        resolutionBox.setSpacing(10);
        //add slider for Sound Volume
        Label soundLabel = new Label("Sound Volume");
        Slider soundSlider = new Slider();
        soundSlider.setMin(0);
        soundSlider.setMax(100);
        soundSlider.setValue(Settings.effectVolume);
        soundSlider.setShowTickLabels(true);
        soundSlider.setShowTickMarks(true);
        soundSlider.setMajorTickUnit(50);
        soundSlider.setMinorTickCount(5);
        soundSlider.setBlockIncrement(10);
        soundSlider.valueProperty().addListener((ov, old_val, new_val) -> Settings.effectVolume = (double) new_val);
        HBox soundBox = new HBox(soundLabel,soundSlider);
        soundBox.setAlignment(Pos.CENTER);
        //add slider for Music Volume
        Label musicLabel = new Label("Music Volume");
        Slider musicSlider = new Slider();
        musicSlider.setMin(0);
        musicSlider.setMax(100);
        musicSlider.setValue(Settings.musicVolume);
        musicSlider.setShowTickLabels(true);
        musicSlider.setShowTickMarks(true);
        musicSlider.setMajorTickUnit(50);
        musicSlider.setMinorTickCount(5);
        musicSlider.setBlockIncrement(10);
        musicSlider.valueProperty().addListener((ov, old_val, new_val) -> {
            Settings.musicVolume = (double) new_val;
            StartMenu.getInstance().setVolume(Settings.musicVolume);
        });
        HBox musicBox = new HBox(musicLabel,musicSlider);
        musicBox.setAlignment(Pos.CENTER);
        ExitButton exitButton = new ExitButton();
        exitButton.setOnAction(event -> Settings.changeRoot(StartMenu.getInstance()));
        instance.setStyle(Settings.menuStyle);
        instance.getChildren().addAll(resolutionBox,soundBox,musicBox,exitButton);
    }
}
