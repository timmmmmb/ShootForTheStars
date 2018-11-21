package game.entitiy;

import javafx.scene.Group;

public class UpAndDownEnemy extends EnemyBasic{
    boolean moveup = false;
    public UpAndDownEnemy(Group bullets) {
        super(bullets);
        shootercdmax = 120;
        speed = 2;
    }

    @Override
    public void move() {
        if(moveup){
            movePosition(-speed/2,-speed*2);
        }else{
            movePosition(-speed/2,speed*2);
        }
        if(characterModel.intersects(0,-20,2000,20)||characterModel.intersects(0,720,2000,20)){
            moveup=!moveup;
        }
    }
}
