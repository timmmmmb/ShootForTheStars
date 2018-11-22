package game.entitiy;

import javafx.scene.Group;

import java.util.Random;

public class TargetEnemy extends EnemyBasic{
    double targetX, targetY;
    double xSpeed, ySpeed;
    public TargetEnemy(Group bullets, double x, int y) {
        super(bullets);
        Random rng = new Random();
        setPosition(x,y);
        this.targetX =rng.nextDouble()*720;
        this.targetY =0;
        double hypothenuse = Math.sqrt((characterModel.getX()-this.targetX)*(characterModel.getX()-this.targetX)+(characterModel.getY()-this.targetY)*(characterModel.getY()-this.targetY));
        double steps = hypothenuse / speed;
        xSpeed = (characterModel.getX()-targetX)/steps;
        ySpeed = (characterModel.getY()-targetY)/steps;
        System.out.println(targetX+" "+targetY+" "+xSpeed+" "+ySpeed);
    }

    @Override
    public void move() {
        movePosition(-xSpeed,ySpeed);
    }
}
