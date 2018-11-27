package game.entitiy;

import game.bullet.BaseBullet;
import game.image.ImageLoader;
import game.settings.Settings;
import game.sound.SoundLoader;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.ArrayList;


public class EnemyBasic extends Entity {

    private int points = 10;
    private Group bulletsScene;
    int shootercdmax = 60;
    private int shootercd = 0;

    public EnemyBasic(Group bullets) {
        super(  ImageLoader.getInstance().getAnimationImagesRed(),
                ImageLoader.getInstance().getExplosionImages(),
                ImageLoader.getInstance().getBulletRed(),
                ImageLoader.getInstance().getBulletRedEffect(),
                ImageLoader.getInstance().getRedShield(),
                270);
        bulletsScene = bullets;
        speed = 2;
    }

    EnemyBasic(Group bullets, ArrayList<Image> animation) {
        super(  animation,
                ImageLoader.getInstance().getExplosionImages(),
                ImageLoader.getInstance().getBulletRed(),
                ImageLoader.getInstance().getBulletRedEffect(),
                ImageLoader.getInstance().getRedShield(),
                270);
        bulletsScene = bullets;
        speed = 2;
    }

    EnemyBasic(Group bullets, ArrayList<Image> animationImages, ArrayList<Image> deathAnimationImages, Image bulletImage, ArrayList<Image> bulletEffect, Image shieldImage) {
        super(  animationImages,
                deathAnimationImages,
                bulletImage,
                bulletEffect,
                shieldImage,
                270);
        bulletsScene = bullets;
        speed = 2;
    }

    public void update(){
        animate();
        if(!isDead()){
            move();
            if(shootercdmax==shootercd){
                shootercd = 0;
                shoot();
            }else{
                shootercd++;
            }
        }
    }

    public void move(){
        movePosition(-speed,0.0);
    }

    @Override
    public void shoot(){
        bulletsScene.getChildren().add(new BaseBullet(characterModel.getX(),characterModel.getY()+(characterModel.getImage().getHeight()/2),-bulletspeed,0,bulletImage,rotation,bulletEffect));
        SoundLoader.getInstance().getShooteEffect().play(Settings.effectVolume);
    }

    public int getPoints() {
        return points;
    }

    @Override
    void animate(){
        super.animate();
    }
}