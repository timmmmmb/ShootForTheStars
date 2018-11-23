package game.entitiy;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MotherShip extends  EnemyBasic{
    private double rotate;
    public MotherShip(Group bullets) {
        super("Red/mothership_try.png",bullets);
        speed = 1;
        getShield();
    }

    @Override
    void loadImages(){
        this.bulletImage = new Image("Red/bullet_red.png",100,100,true,false);
        this.shieldImage = new Image("Red/Enemy_animation/shield.png",200,200,true,false);
        shieldView = new ImageView(shieldImage);
        animationImages.add(new Image(animationImageUrl,200,200,true,false));
        int maxDeathAnimations = 16;
        for(int i = 0; i<= maxDeathAnimations; i++){
            deathAnimationImages.add(new Image(deathImageURL+i+".png",200,200,true,false));
        }
        characterModel = new ImageView(animationImages.get(0));
        characterModel.setRotate(rotation);
        this.getChildren().add(characterModel);
        this.getChildren().add(shieldView);
        this.getChildren().add(bullets);
        shieldView.setVisible(true);
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
