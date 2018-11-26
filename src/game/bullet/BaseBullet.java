package game.bullet;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class BaseBullet extends ImageView {

    private ArrayList<Image> deathAnimationImages;
    private boolean dead = false;
    private boolean remove = false;
    private final double xspeed;
    private final double yspeed;
    private int animateionFrame = 0;
    public BaseBullet(double x, double y, double xspeed, double yspeed, Image image, double rotation, ArrayList<Image> deathAnimationImages) {
        super(image);
        this.deathAnimationImages = deathAnimationImages;
        setX(x);
        setY(y-image.getHeight()/2);
        setRotate(rotation);
        this.xspeed = xspeed;
        this.yspeed = yspeed;
    }

    public void move(){
        if(animateionFrame==deathAnimationImages.size()&&dead){
            remove = true;
        }else if(dead){
            setImage(deathAnimationImages.get(animateionFrame));
            animateionFrame++;
        }else{
            setX(getX()+xspeed);
            setY(getY()+yspeed);
        }
    }

    public void hit(){
        dead = true;
    }
    public boolean isRemove() {
        return remove;
    }
    public boolean isDead() {
        return dead;
    }
}
