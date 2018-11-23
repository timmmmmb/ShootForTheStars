package game.entitiy;

import game.bullet.BaseBullet;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Entity extends Group {
    double speed = 5;
    ImageView characterModel = new ImageView();
    boolean shield = false;
    int maxSprites = 8;
    private boolean invincible = false;
    int animationTimer = 0;
    boolean dead = false;
    boolean remove = false;

    ArrayList<Image> animationImages = new ArrayList<>();
    ArrayList<Image> deathAnimationImages = new ArrayList<>();
    Image bulletImage;
    Image shieldImage;
    ImageView shieldView;
    final String  deathImageURL;
    final String  animationImageUrl;
    final double rotation;
    final double bulletspeed = 10;
    final int bulletcd = 30;
    final String deathAnimationBulletURL;
    int bulletTimer;
    Group bullets = new Group();
    Entity(String deathImageURL, String animationImageUrl, double rotation, String bulletURL, String shieldURL, String deathAnimationBulletURL){
        this.bulletImage = new Image(bulletURL,50,50,true,false);
        this.shieldImage = new Image(shieldURL,100,100,true,false);
        this.rotation = rotation;
        this.deathImageURL = deathImageURL;
        this.animationImageUrl = animationImageUrl;
        this.deathAnimationBulletURL = deathAnimationBulletURL;
        loadImages();
    }

    Entity(String deathImageURL, String animationImageUrl, double rotation, String bulletURL, String shieldURL, int maxSprites,String deathAnimationBulletURL){
        this.maxSprites = maxSprites;
        this.bulletImage = new Image(bulletURL,50,50,true,false);
        this.shieldImage = new Image(shieldURL,100,100,true,false);
        this.rotation = rotation;
        this.deathImageURL = deathImageURL;
        this.animationImageUrl = animationImageUrl;
        this.deathAnimationBulletURL = deathAnimationBulletURL;
        loadImages();
    }

    public Entity(String deathImageURL, String animationImageUrl, int rotation, String bulletURL, String shieldURL, String deathAnimationBulletURL, int maxSprites) {
        this.bulletImage = new Image(bulletURL,50,50,true,false);
        this.shieldImage = new Image(shieldURL,100,100,true,false);
        this.rotation = rotation;
        this.deathImageURL = deathImageURL;
        this.animationImageUrl = animationImageUrl;
        this.maxSprites = maxSprites;
        this.deathAnimationBulletURL = deathAnimationBulletURL;
        loadImages();
    }

    void loadImages(){
        shieldView = new ImageView(shieldImage);
        for(int i = 1; i<= maxSprites; i++){
            animationImages.add(new Image(animationImageUrl+i+".png",100,100,true,false));
        }
        int maxDeathAnimations = 16;
        for(int i = 0; i<= maxDeathAnimations; i++){
            deathAnimationImages.add(new Image(deathImageURL+i+".png",100,100,true,false));
        }
        characterModel = new ImageView(animationImages.get(0));
        characterModel.setRotate(rotation);
        this.getChildren().add(characterModel);
        this.getChildren().add(shieldView);
        this.getChildren().add(bullets);
        shieldView.setVisible(false);
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
        bullets.getChildren().add(new BaseBullet(characterModel.getX()+characterModel.getImage().getWidth(),characterModel.getY()+(characterModel.getImage().getHeight()/2),bulletspeed,0,bulletImage,rotation,deathAnimationBulletURL));
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
