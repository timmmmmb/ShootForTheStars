package game.entitiy;

import game.image.ImageLoader;
import javafx.scene.Group;

public class MotherShip extends  EnemyBasic{
    private double rotate;
    public MotherShip(Group bullets) {
        super(bullets, ImageLoader.getInstance().getMotherShip(), ImageLoader.getInstance().getExplosionImages(),ImageLoader.getInstance().getMotherShipBullet(),ImageLoader.getInstance().getBulletRedEffect(),ImageLoader.getInstance().getRedMothershipShield());
        speed = 1;
        getShield();
    }

    @Override
    void animate() {
        super.animate();
        if (!dead) {
            characterModel.setRotate(rotate);
            rotate++;
            if(rotate == 360){
                rotate = 0;
            }
        }
    }


}
