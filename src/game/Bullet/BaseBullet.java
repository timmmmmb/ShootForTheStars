package game.Bullet;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BaseBullet extends ImageView {
    private final double xspeed;
    private final double yspeed;
    public BaseBullet(double x,double y,double xspeed, double yspeed, Image image, double rotation) {
        super(image);
        setX(x);
        setY(y-image.getHeight()/2);
        setRotate(rotation);
        this.xspeed = xspeed;
        this.yspeed = yspeed;
    }

    public void move(){
        setX(getX()+xspeed);
        setY(getY()+yspeed);
    }
}
