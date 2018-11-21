package game.entitiy;

public class Player extends Entity {
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean shooting = false;
    public Player() {
        super("Effects\\Blue Effects\\1_","Blue\\Animation\\" ,90, "Blue\\bullet.png");
    }

    private void movePlayer(){
        double movex = 0,movey = 0;
        if(up)movey-=this.speed;
        if(down)movey+=this.speed;
        if(left)movex-=this.speed;
        if(right)movex+=this.speed;
        movePosition(movex,movey);
    }

    public void updatePlayer(){
        if(shooting&&bulletTimer==bulletcd){
            shoot();
            bulletTimer = 0;
        }
        if(bulletTimer!=bulletcd){
            bulletTimer++;
        }
        movePlayer();
        moveBullets();
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
