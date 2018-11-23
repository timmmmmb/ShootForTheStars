package game.entitiy;

import game.image.ImageLoader;
import javafx.scene.Group;

public class SmallShip extends EnemyBasic {
    MotherShip motherShip;
    private double radius = 250;
    private double theta = 0;
    private double thetaIncrement = 0.01;
    public SmallShip(Group bullets, MotherShip motherShip) {
        super(bullets, ImageLoader.getInstance().getAnimationSmallShip());
        this.motherShip = motherShip;
    }

    public SmallShip(Group bullets) {
        super(bullets, ImageLoader.getInstance().getAnimationSmallShip());
    }

    @Override
    public void move(){
        if(motherShip!=null&&!motherShip.isDead()){
            setPosition(motherShip.getCenterX() - (int) (Math.sin(theta) * this.radius), //
                    motherShip.getCenterY()- (int) (Math.cos(theta) * this.radius));
            this.theta -= this.thetaIncrement;
        }else{
            movePosition(-speed,0.0);
        }
    }

    public double getTheta(){
        return theta;
    }

    public double getRadius(){
        return radius;
    }

    public void setTheta(double theta){
        this.theta = theta;
    }
}
