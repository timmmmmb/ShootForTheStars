package game.image;

import game.settings.Settings;
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
    private Image backgroundImage;
    private Image redShield;
    private Image blueShield;
    private Image redMothershipShield;
    private Image bulletRed;
    private Image bulletBlue;
    private ArrayList<Image> motherShip = new ArrayList<>();
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
        double size = 1.0*Settings.width/1080;
        //load Background
        backgroundImage = new Image("Images/Background/background.jpg", Settings.width,Settings.width,true,false);
        //load shields
        redShield = new Image("Images/Red/Enemy_animation/shield.png",100*size,100*size,true,false);
        redMothershipShield = new Image("Images/Red/Enemy_animation/shield.png",200*size,200*size,true,false);
        blueShield = new Image("Images/Blue/Animation/shield.png",100*size,100*size,true,false);
        //load animation images
        for(int i = 1; i<= 8; i++){
            animationImagesRed.add(new Image("Images/Red/Enemy_animation/" +i+".png",100*size,100*size,true,false));
        }
        for(int i = 1; i<= 8; i++){
            animationImagesBlue.add(new Image("Images/Blue/Animation/" +i+".png",100*size,100*size,true,false));
        }
        //load small shipanimation
        for(int i = 1; i<= 5; i++){
            animationSmallShip.add(new Image("Images/Red/small_ship_animation/" +i+".png",75*size,75*size,true,false));
        }
        //load mothership image
        motherShip.add(new Image("Images/Red/mothership_try.png",200*size,200*size,true,false));
        //load explosionimages
        for(int i = 0; i<= 16; i++){
            explosionImages.add(new Image("Images/Effects/Galaxy/galaxy_" +i+".png",100*size,100*size,true,false));
        }
        //load bullet images
        motherShipBullet = new Image("Images/Red/bullet_red.png",100*size,100*size,true,false);
        bulletRed = new Image("Images/Red/bullet_red.png",50*size,50*size,true,false);
        bulletBlue = new Image("Images/Blue/bullet.png",50*size,50*size,true,false);
        for(int i = 0; i<= 16; i++){
            bulletBlueEffect.add(new Image("Images/Effects/Blue Effects/1_" +i+".png",100*size,100*size,true,false));
        }
        for(int i = 0; i<= 16; i++){
            bulletRedEffect.add(new Image("Images/Effects/Red Explosion/1_" +i+".png",100*size,100*size,true,false));
        }
        meteors.add(new Image("Images/Aestroids/aestroid_brown.png",100*size,100*size,true,false));
        meteors.add(new Image("Images/Aestroids/aestroid_dark.png",100*size,100*size,true,false));
        meteors.add(new Image("Images/Aestroids/aestroid_gay_2.png",100*size,100*size,true,false));
        meteors.add(new Image("Images/Aestroids/aestroid_gray.png",100*size,100*size,true,false));
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

    public Image getBackgroundImage() {
        return backgroundImage;
    }
}
