package game.entitiy;

import game.image.ImageLoader;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Meteor extends Entity {
    public Meteor(double x, double y, ArrayList<Image> animationImage) {
        super(animationImage, ImageLoader.getInstance().getExplosionImages(),ImageLoader.getInstance().getBulletRed(),ImageLoader.getInstance().getBulletRedEffect(),ImageLoader.getInstance().getRedShield(),270);
        setPosition(x,y);
        speed = 1;
        shieldView.setVisible(false);
    }

    public void update(){
        animate();
        move();
    }

    private void move(){
        movePosition(-speed,0.0);
    }

    @Override
    void animate(){
        setRotate(getRotate()+0.5%360);
    }

}
