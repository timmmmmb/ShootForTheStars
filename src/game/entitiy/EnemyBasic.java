package game.entitiy;

import game.bullet.BaseBullet;
import javafx.scene.Group;


public class EnemyBasic extends Entity {

    private int points = 10;
    private Group bulletsScene;
    int shootercdmax = 60;
    private int shootercd = 0;

    public EnemyBasic(Group bullets) {
        super("Effects/Galaxy/galaxy_","Red/Enemy_animation/" ,270,"Red/bullet_red.png","Red/Enemy_animation/shield.png","Effects/Red Explosion/1_");
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
        bulletsScene.getChildren().add(new BaseBullet(characterModel.getX(),characterModel.getY()+(characterModel.getImage().getHeight()/2),-bulletspeed,0,bulletImage,rotation,deathAnimationBulletURL));
    }

    public int getPoints() {
        return points;
    }
}