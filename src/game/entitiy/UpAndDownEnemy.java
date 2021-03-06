package game.entitiy;

import game.settings.Settings;
import javafx.scene.Group;

public class UpAndDownEnemy extends EnemyBasic{
    private boolean moveup = false;
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
        if(characterModel.intersects(0,-20,3000,20)||characterModel.intersects(0, Settings.height,3000,20)){
            moveup=!moveup;
        }
    }
}
