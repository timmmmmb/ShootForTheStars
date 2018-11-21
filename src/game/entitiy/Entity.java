package game.entitiy;

import game.Bullet.BaseBullet;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Entity extends Group {
    double speed = 5;
    ImageView characterModel = new ImageView();
    boolean invincible = false;
    private int animationTimer = 0;
    private int maxSprites = 8;
    private boolean dead = false;
    private boolean remove = false;
    private ArrayList<Image> animationImages = new ArrayList<Image>();
    private ArrayList<Image> deathAnimationImages = new ArrayList<Image>();
    final Image bulletImage;
    private int maxDeathAnimations = 16;
    private final String  deathImageURL;
    private final String  animationImageUrl;
    final double rotation;
    final double bulletspeed = 10;
    final int bulletcd = 30;
    int bulletTimer;
    private Group bullets = new Group();
    public Entity(String deathImageURL, String animationImageUrl, double rotation, String bulletURL){
        this.bulletImage = new Image(bulletURL,50,50,true,false);
        this.rotation = rotation;
        this.deathImageURL = deathImageURL;
        this.animationImageUrl = animationImageUrl;
        loadImages();
    }

    private void loadImages(){
        for(int i = 1; i<=maxSprites;i++){
            animationImages.add(new Image(animationImageUrl+i+".png",100,100,true,false));
        }
        for(int i = 0; i<=maxDeathAnimations;i++){
            deathAnimationImages.add(new Image(deathImageURL+i+".png",100,100,true,false));
        }
        characterModel = new ImageView(animationImages.get(0));
        characterModel.setRotate(rotation);
        this.getChildren().add(characterModel);
        this.getChildren().add(bullets);
    }

    public void animate(){
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
    }

    public boolean isRemove() {
        return remove;
    }

    public void die() {
        dead = true;
        animationTimer = 0;
    }

    public boolean isDead() {
        return dead;
    }

    public void setPosition(double x, double y){
        characterModel.setX(x);
        characterModel.setY(y);
    }

    public double getHeight(){
        return characterModel.getImage().getHeight();
    }

    public double getWidth(){
        return characterModel.getImage().getWidth();
    }

    public void movePosition(double x, double y){
        setPosition(characterModel.getX()+x,characterModel.getY()+y);
    }

    public void shoot(){
        bullets.getChildren().add(new BaseBullet(characterModel.getX()+characterModel.getImage().getWidth(),characterModel.getY()+(characterModel.getImage().getHeight()/2),bulletspeed,0,bulletImage,rotation));
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
}
