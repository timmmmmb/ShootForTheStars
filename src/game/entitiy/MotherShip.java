package game.entitiy;

import javafx.scene.Group;

public class MotherShip extends  EnemyBasic{
    private double rotate;
    public MotherShip(Group bullets) {
        super("Red/mothership_try.png",bullets);
        speed = 1;
        getShield();
    }

    @Override
    void animate() {
        if (animationTimer == deathAnimationImages.size() - 1 && dead) {
            remove = true;
        } else if (animationTimer == animationImages.size() - 1 && !dead) {
            animationTimer = 0;
        } else {
            animationTimer++;
        }
        if (!dead) {
            characterModel.setRotate(rotate);
            rotate++;
            if(rotate == 360){
                rotate = 0;
            }
        } else {
            characterModel.setImage(deathAnimationImages.get(animationTimer));
        }
        if (shield) {
            shieldView.setVisible(true);
        } else {
            shieldView.setVisible(false);
        }
    }


}
