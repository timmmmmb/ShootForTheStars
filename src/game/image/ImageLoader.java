package game.image;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * this class will load all of the images at the start of the game so it no longer loads them mid game and it stops lag
 */
public class ImageLoader {
    private static ImageLoader instance;
    private ArrayList<Image> animationImagesBlue = new ArrayList<>();
    private ArrayList<Image> animationImagesRed = new ArrayList<>();
    private ArrayList<Image> explosionImages = new ArrayList<>();
    private ArrayList<Image> animationSmallShip = new ArrayList<>();
    private Image redShield;
    private Image blueShield;
    private Image redMothershipShield;
    private Image bulletRed;
    private Image bulletBlue;
    private ArrayList<Image> motherShip;
    private ArrayList<Image> bulletBlueEffect = new ArrayList<>();
    private ArrayList<Image> bulletRedEffect = new ArrayList<>();
    private ArrayList<Image> meteors = new ArrayList<>();
    private Image motherShipBullet;

    private ImageLoader(){
        initializeImages();
    }

    /**
     * loads all the images
     */
    private void initializeImages() {
        //load shields
        redShield = new Image("Red/Enemy_animation/shield.png",100,100,true,false);
        redMothershipShield = new Image("Red/Enemy_animation/shield.png",200,200,true,false);
        blueShield = new Image("Blue/Animation/shield.png",100,100,true,false);
        //load animation images
        for(int i = 1; i<= 8; i++){
            animationImagesRed.add(new Image("Red/Enemy_animation/"+i+".png",100,100,true,false));
        }
        for(int i = 1; i<= 8; i++){
            animationImagesBlue.add(new Image("Blue/Animation/"+i+".png",100,100,true,false));
        }
        //load small shipanimation
        for(int i = 1; i<= 5; i++){
            animationSmallShip.add(new Image("Red/small_ship_animation/"+i+".png",75,75,true,false));
        }
        //load mothership image
        motherShip .add(new Image("Red/mothership_try.png",200,200,true,false));
        //load explosionimages
        for(int i = 0; i<= 16; i++){
            explosionImages.add(new Image("Effects/Galaxy/galaxy_"+i+".png",100,100,true,false));
        }
        //load bullet images
        motherShipBullet = new Image("Red/bullet_red.png",100,100,true,false);
        bulletRed = new Image("Red/bullet_red.png",50,50,true,false);
        bulletBlue = new Image("Blue/bullet.png",50,50,true,false);
        for(int i = 0; i<= 16; i++){
            bulletBlueEffect.add(new Image("Effects/Blue Effects/1_"+i+".png",100,100,true,false));
        }
        for(int i = 0; i<= 16; i++){
            bulletRedEffect.add(new Image("Effects/Red Explosion/1_"+i+".png",100,100,true,false));
        }
        meteors.add(new Image("Aestroids/aestroid_brown"));
        meteors.add(new Image("Aestroids/aestroid_dark"));
        meteors.add(new Image("Aestroids/aestroid_gay_2"));
        meteors.add(new Image("Aestroids/aestroid_gray"));
    }

    public static ImageLoader getInstance(){
        if(instance==null){
            instance = new ImageLoader();
        }
        return instance;
    }

    public Image getRedShield() {
        return redShield;
    }

    public Image getBlueShield() {
        return blueShield;
    }

    public Image getRedMothershipShield() {
        return redMothershipShield;
    }

    public Image getBulletRed() {
        return bulletRed;
    }

    public Image getBulletBlue() {
        return bulletBlue;
    }

    public ArrayList<Image> getMotherShip() {
        return motherShip;
    }

    public Image getMotherShipBullet() {
        return motherShipBullet;
    }

    public ArrayList<Image> getAnimationImagesBlue() {
        return animationImagesBlue;
    }

    public ArrayList<Image> getAnimationImagesRed() {
        return animationImagesRed;
    }

    public ArrayList<Image> getAnimationSmallShip() {
        return animationSmallShip;
    }

    public ArrayList<Image> getExplosionImages() {
        return explosionImages;
    }

    public ArrayList<Image> getBulletBlueEffect() {
        return bulletBlueEffect;
    }

    public ArrayList<Image> getBulletRedEffect() {
        return bulletRedEffect;
    }

    public ArrayList<Image> getMeteors() {
        return meteors;
    }
}
