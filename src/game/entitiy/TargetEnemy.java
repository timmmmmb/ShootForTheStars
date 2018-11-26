package game.entitiy;

import game.settings.Settings;
import javafx.scene.Group;

import java.util.Random;

public class TargetEnemy extends EnemyBasic{
    private double xSpeed, ySpeed;
    public TargetEnemy(Group bullets, double x, int y) {
        super(bullets);
        Random rng = new Random();
        setPosition(x,y);
        double targetX = 0;
        double targetY = rng.nextDouble() * Settings.height;
        double hypothenuse = Math.sqrt((x- targetX)*(x- targetX)+(y- targetY)*(y- targetY));
        double steps = hypothenuse / speed;
        xSpeed = (x- targetX)/steps;
        ySpeed = ((double)y- targetY)/steps;
    }

    @Override
    public void move() {
        movePosition(-xSpeed,ySpeed);
    }
}
