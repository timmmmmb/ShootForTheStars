package game.bullet;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class BaseBullet extends ImageView {

    private ArrayList<Image> deathAnimationImages = new ArrayList<>();
    private final String  deathImageURL;
    private boolean dead = false;
    private boolean remove = false;
    private final double xspeed;
    private final double yspeed;
    private int animateionFrame = 0;
    public BaseBullet(double x,double y,double xspeed, double yspeed, Image image, double rotation, String deathImageURL) {
        super(image);
        this.deathImageURL = deathImageURL;
        setX(x);
        setY(y-image.getHeight()/2);
        setRotate(rotation);
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        loadSprites();
    }

    private void loadSprites(){
        int maxDeathAnimations = 16;
        for(int i = 0; i<= maxDeathAnimations; i++){
            deathAnimationImages.add(new Image(deathImageURL+i+".png",100,100,true,false));
        }
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
