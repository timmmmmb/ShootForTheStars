package game.entitiy;

import game.image.ImageLoader;

public class Player extends Entity {
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean shooting = false;
    public Player() {
        super(  ImageLoader.getInstance().getAnimationImagesBlue(),
                ImageLoader.getInstance().getExplosionImages(),
                ImageLoader.getInstance().getBulletBlue(),
                ImageLoader.getInstance().getBulletBlueEffect(),
                ImageLoader.getInstance().getBlueShield(),
                90);
        getShield();
        //setInvincible(true);
    }

    private void movePlayer(){
        if(isDead()){
            return;
        }
        double movex = 0,movey = 0;
        if(up)movey-=this.speed;
        if(down)movey+=this.speed;
        if(left)movex-=this.speed;
        if(right)movex+=this.speed;
        movePosition(movex,movey);
        if(getCharacterModel().getX()<0){
            getCharacterModel().setX(0);
        }
        if(getCharacterModel().getX()+getWidth()>=getScene().getWidth()){
            getCharacterModel().setX(getScene().getWidth()-getWidth());
        }
        if(getCharacterModel().getY()<0){
            getCharacterModel().setY(0);
        }
        if(getCharacterModel().getY()+getHeight()>=getScene().getHeight()){
            getCharacterModel().setY(getScene().getHeight()-getHeight());
        }
        shieldView.setX(getCharacterModel().getX());
        shieldView.setY(getCharacterModel().getY());
    }

    public void updatePlayer(){
        if(shooting&&bulletTimer==bulletcd&&!isDead()){
            shoot();
            bulletTimer = 0;
        }
        if(bulletTimer!=bulletcd&&!isDead()){
            bulletTimer++;
        }
        movePlayer();
        moveBullets();
        animate();
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
