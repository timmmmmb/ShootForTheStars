package game.entitiy;

import game.Bullet.BaseBullet;
import javafx.scene.Group;


public class EnemyBasic extends Entity {

    private Group bulletsScene;

    public EnemyBasic(Group bullets) {
        super("Effects\\Red Explosion\\1_","Red\\Enemy_animation\\" ,270,"Red\\bullet_red.png");
        bulletsScene = bullets;
    }

    public void move(){
        if(!isDead()){
            movePosition(-0.5,0.0);
        }
    }

    @Override
    public void shoot(){
        bulletsScene.getChildren().add(new BaseBullet(characterModel.getX(),characterModel.getY()+(characterModel.getImage().getHeight()/2),-bulletspeed,0,bulletImage,rotation));
    }
}