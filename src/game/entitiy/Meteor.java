package game.entitiy;

public class Meteor extends Entity {
    public Meteor(double x, double y, String animationImageUrl) {
        super("Effects/Red Explosion/1_",animationImageUrl ,270,"Red/bullet_red.png","Red/Enemy_animation/shield.png",1);
        setPosition(x,y);
        speed = 1;
    }

    public void update(){
        animate();
        move();
    }

    private void move(){
        movePosition(-speed,0.0);
    }

    @Override
    void animate(){
        setRotate(getRotate()+0.5%360);
    }

}
