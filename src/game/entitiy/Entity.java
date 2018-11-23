package game.entitiy;

import game.bullet.BaseBullet;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Entity extends Group {
    double speed = 5;
    boolean shield = false;
    int maxSprites = 8;
    private boolean invincible = false;
    int animationTimer = 0;
    boolean dead = false;
    boolean remove = false;

    ImageView characterModel = new ImageView();
    ImageView shieldView;

    final ArrayList<Image> animationImages;
    final ArrayList<Image> deathAnimationImages;
    final ArrayList<Image> bulletEffect;
    final Image bulletImage;
    final Image shieldImage;
    final double rotation;
    final double bulletspeed = 10;
    final int bulletcd = 30;
    int bulletTimer;
    Group bullets = new Group();

    public Entity(ArrayList<Image> animationImages, ArrayList<Image> deathAnimationImages,Image bulletImage, ArrayList<Image> bulletEffect,  Image shieldImage, double rotation) {
        this.animationImages = animationImages;
        this.deathAnimationImages = deathAnimationImages;
        this.bulletEffect = bulletEffect;
        this.bulletImage = bulletImage;
        this.shieldImage = shieldImage;
        this.rotation = rotation;
    }

    void animate(){
        if(animationTimer==deathAnimationImages.size()-1&&dead){
            remove = true;
        }else if(animationTimer==animationImages.size()-1&&!dead){
            animationTimer=0;
        }else{
            animationTimer++;
        }
        if(!dead){
            characterModel.setImage(animationImages.get(animationTimer));
        }else{
            characterModel.setImage(deathAnimationImages.get(animationTimer));
        }
        if(shield){
            shieldView.setVisible(true);
        }else{
            shieldView.setVisible(false);
        }
    }

    public boolean isRemove() {
        return remove;
    }

    public void hit() {
        if(shield){
            shield =false;
        }else{
            dead = true;
            animationTimer = 0;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void setPosition(double x, double y){
        characterModel.setX(x);
        characterModel.setY(y);
        shieldView.setX(x);
        shieldView.setY(y);
    }

    public double getHeight(){
        return characterModel.getImage().getHeight();
    }

    public double getWidth(){
        return characterModel.getImage().getWidth();
    }

    void movePosition(double x, double y){
        setPosition(characterModel.getX()+x,characterModel.getY()+y);
    }

    public void shoot(){
        bullets.getChildren().add(new BaseBullet(characterModel.getX()+characterModel.getImage().getWidth(),characterModel.getY()+(characterModel.getImage().getHeight()/2),bulletspeed,0,bulletImage,rotation,bulletEffect));
    }

    void moveBullets(){
        for(Node bulletnode:bullets.getChildren()){
            BaseBullet bullet = ((BaseBullet)bulletnode);
            bullet.move();
        }
    }

    public Group getBullets(){
        return bullets;
    }

    public ImageView getCharacterModel(){
        return characterModel;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible=invincible;
    }

    public boolean isShield() {
        return shield;
    }

    public void getShield() {
        shield = true;
    }


    public double getCenterX(){
        return characterModel.getX()+characterModel.getImage().getWidth()/2;
    }

    public double getCenterY(){
        return characterModel.getY()+characterModel.getImage().getHeight()/2;
    }
}
