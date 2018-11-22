package game.entitiy;

import javafx.scene.Group;

public class FollowEnemy extends EnemyBasic {
    private Player target;
    public FollowEnemy(Group bullets, Player target) {
        super(bullets);
        this.target = target;
    }

    @Override
    public void move() {
        if(target.getCharacterModel().getY()>characterModel.getY()){
            movePosition(-speed, +speed);
        }else if(target.getCharacterModel().getY()<characterModel.getY()){
            movePosition(-speed, -speed);
        }else{
            movePosition(-speed, 0);
        }
    }
}
