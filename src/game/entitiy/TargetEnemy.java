package game.entitiy;

import javafx.scene.Group;

import java.util.Random;

public class TargetEnemy extends EnemyBasic{
    private double xSpeed, ySpeed;
    public TargetEnemy(Group bullets, double x, int y) {
        super(bullets);
        Random rng = new Random();
        setPosition(x,y);
        double targetX = 0;
        double targetY = rng.nextDouble() * 720;
        double hypothenuse = Math.sqrt((characterModel.getX()- targetX)*(characterModel.getX()- targetX)+(characterModel.getY()- targetY)*(characterModel.getY()- targetY));
        double steps = hypothenuse / speed;
        xSpeed = (characterModel.getX()- targetX)/steps;
        ySpeed = (characterModel.getY()- targetY)/steps;
        System.out.println(targetX +" "+ targetY +" "+xSpeed+" "+ySpeed);
    }

    @Override
    public void move() {
        movePosition(-xSpeed,ySpeed);
    }
}
