package game.main;

import game.bullet.BaseBullet;
import game.entitiy.*;
import game.image.ImageLoader;
import game.menu.DeathScreen;
import game.menu.GameMenu;
import game.settings.Settings;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class LevelScene extends Group {
    private static Group meteors = new Group();
    private static Group enemys = new Group();
    private static Group bullets = new Group();
    private static Player player = new Player();
    private static int timer = 0;
    private static final int  resetTimer = 1800;
    private static int difficulty = 1;
    private static int score = 0;
    private static Label scoreLabel = new Label("Score: "+score);
    private static boolean spawnMeteors = false;
    private static boolean spawnEnemys = true;
    private static AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if(timer == resetTimer){
                timer = 0;
                difficulty++;
                if(difficulty % 3==0){
                    spawnMotherShip();
                }else{
                    spawnMeteor();
                }
                player.getShield();
                spawnMeteors = !spawnMeteors;
            }
            timer++;
            if(timer%30 ==0&&!player.isDead()){
                increaseScore(1);
            }

            if(timer%250 ==0&&difficulty>=3){
                spawn();
            }

            if(timer%350 ==0&&difficulty>=2){
                spawn();
            }
            //spawn one enemy all 15s
            if(timer%450 ==0&&timer%900!=0){
                spawn();
            }

            if(timer%600 ==0&&spawnMeteors){
                spawnMeteor();
            }

            if(timer%900 ==0){
                spawnThreeUpAndDown();
            }

            if(timer%1100 ==0&&difficulty>=4){
                spawnThreeUpAndDown();
            }

            player.updatePlayer();
            for(Node enemy:enemys.getChildren()){
                ((EnemyBasic)enemy).update();
            }
            for(Node meteor:meteors.getChildren()){
                ((Meteor)meteor).update();
            }
            moveBullets();
            //check the position of all the bullets
            Iterator bulletIterator = player.getBullets().getChildren().iterator();
            loop:while (bulletIterator.hasNext())
            {
                BaseBullet bullet = (BaseBullet)bulletIterator.next();
                for (Object o : enemys.getChildren()) {
                    EnemyBasic enemy = (EnemyBasic) o;
                    //if an enemy is hit
                    if (enemy.getCharacterModel().intersects(bullet.getLayoutBounds()) && !enemy.isDead()&&!bullet.isDead()) {
                        if(!enemy.isInvincible()){
                            enemy.hit();
                            increaseScore(enemy.getPoints());
                        }
                        bullet.hit();
                        continue loop;
                    }
                }
                for (Object o : meteors.getChildren()) {
                    Meteor meteor = (Meteor) o;
                    //if a meteor is hit remove the bullet
                    if (meteor.getCharacterModel().intersects(bullet.getLayoutBounds())&&!bullet.isDead()) {
                        bullet.hit();
                        continue loop;
                    }
                }

                //if bullet out of screen
                if (!bullet.intersects(-100, -100, Settings.width + Settings.width, Settings.height + 100)) {
                    bulletIterator.remove();
                }

                if(bullet.isRemove()){
                    bulletIterator.remove();
                }
            }
            Iterator enemyIterator = enemys.getChildren().iterator();
            while (enemyIterator.hasNext()) {
                EnemyBasic enemy = (EnemyBasic) enemyIterator.next();
                //if the player collides
                if (player.getCharacterModel().intersects(enemy.getLayoutBounds()) && !player.isDead() && !enemy.isDead()) {
                    if (!enemy.isInvincible()) {
                        enemy.hit();
                        increaseScore(enemy.getPoints());
                    }
                    if (!player.isInvincible()) player.hit();
                    continue;
                }
                //if an enemy is exploded
                if (enemy.isRemove()) {
                    enemyIterator.remove();
                    continue;
                }
                //if enemy out of screen
                if (!enemy.intersects(-100, -100, Settings.width + Settings.width, Settings.height + 100)) {
                    enemyIterator.remove();
                }

                //if an enemy collides with a meteor
                for(Object o:meteors.getChildren()){
                    if(((Meteor)o).intersects(enemy.getLayoutBounds())&& !enemy.isDead()){
                        if (!enemy.isInvincible()) {
                            enemy.hit();
                        }
                    }
                }
            }

            Iterator meteorIterator = meteors.getChildren().iterator();
            while (meteorIterator.hasNext()) {
                Meteor meteor = (Meteor) meteorIterator.next();
                //if the player collides
                if (player.getCharacterModel().intersects(meteor.getLayoutBounds()) && !player.isDead()) {
                    if (!player.isInvincible()) player.hit();
                    continue;
                }
                //if a meteor is exploded
                if (meteor.isRemove()) {
                    meteorIterator.remove();
                    continue;
                }
                //if meteor out of screen
                if (!meteor.intersects(-100, -100, Settings.width + Settings.width, Settings.height + 100)) {
                    meteorIterator.remove();
                }
            }

            bulletIterator = bullets.getChildren().iterator();
            loop:while (bulletIterator.hasNext())
            {
                BaseBullet bullet = (BaseBullet)bulletIterator.next();
                //if the player is hit
                if(player.getCharacterModel().intersects(bullet.getLayoutBounds())&&!player.isDead()&&!bullet.isDead()){
                    if(!player.isInvincible())player.hit();
                    bullet.hit();
                    continue;
                }
                //if bullet out of screen
                if(!bullet.intersects(-100,-100,Settings.width+100,Settings.height+100)){
                    bulletIterator.remove();
                    continue;
                }
                for (Object o : meteors.getChildren()) {
                    Meteor meteor = (Meteor) o;
                    //if a meteor is hit remove the bullet
                    if (meteor.getCharacterModel().intersects(bullet.getLayoutBounds())&&!bullet.isDead()) {
                        bullet.hit();
                        continue loop;
                    }
                }
                if(bullet.isRemove()){
                    bulletIterator.remove();
                }
            }


            if(player.isRemove()){
                animationTimer.stop();
                //gameover change scene to gameOverScene
                DeathScreen.getInstance().setScore(score);
                Settings.changeRoot(DeathScreen.getInstance());
            }
        }
    };
    private static final String menuStyle = "-fx-border-color: #000000; -fx-border-width: 5px;-fx-background-color:#000000;-fx-font-size: 24px;-fx-font-family:Segoe UI;fx-text-fill:#ffffff;";
    private static LevelScene instance;

    private static void moveBullets(){
        for(Node bulletnode:bullets.getChildren()){
            BaseBullet bullet = ((BaseBullet)bulletnode);
            bullet.move();
        }
    }

    private static void increaseScore(int amount){
        score += amount;
        scoreLabel.setText("Score: "+score);
    }

    private LevelScene() {
        super();
    }


    public static LevelScene getInstance(){
        if(instance==null){
            instance = new LevelScene();
            instance.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP: case W:   player.setUp(true);break;
                    case DOWN: case S:  player.setDown(true); break;
                    case LEFT: case A:  player.setLeft(true); break;
                    case RIGHT: case D: player.setRight(true); break;
                    case R: restartLevel(); break;
                    case SPACE:  player.setShooting(true); break;
                    case ESCAPE: GameMenu.getInsctance(animationTimer).setVisible(true); animationTimer.stop();break;

                }
            });
            instance.setOnKeyReleased(event -> {
                switch (event.getCode()) {
                    case UP: case W:   player.setUp(false);break;
                    case DOWN: case S:  player.setDown(false); break;
                    case LEFT: case A:  player.setLeft(false); break;
                    case RIGHT: case D: player.setRight(false); break;
                    case SPACE:  player.setShooting(false); break;
                }
            });
            Settings.stage.getScene().onKeyPressedProperty().bind(instance.onKeyPressedProperty());
            Settings.stage.getScene().onKeyReleasedProperty().bind(instance.onKeyReleasedProperty());
            animationTimer.start();
        }
        return instance;

    }

    private static void createGUI() {
        ImageView backgroundImageView = new ImageView(ImageLoader.getInstance().getBackgroundImage());
        instance.setStyle(menuStyle);
        instance.getChildren().clear();
        instance.getChildren().addAll(backgroundImageView,scoreLabel,player,enemys,bullets,meteors,GameMenu.getInsctance(animationTimer));
        player.setPosition(0,(Settings.height-player.getHeight())/2);
        instance.setStyle(Settings.menuStyle);
        spawn();
    }

    public static void restartLevel(){
        animationTimer.stop();
        enemys = new Group();
        bullets = new Group();
        player = new Player();
        meteors = new Group();
        score = 0;
        scoreLabel = new Label("Score: "+score);
        timer = 0;
        difficulty = 1;
        createGUI();
        animationTimer.start();
    }


    private static void spawn(){
        if(!spawnEnemys)return;
        //randomize the enemy and his startpos
        Random rng = new Random();
        int enemytype = rng.nextInt(4);
        int y = rng.nextInt((int)Settings.height-100)+1;
        //enemytype = 3;
        if(enemytype==0){
            UpAndDownEnemy enemy = new UpAndDownEnemy(bullets);
            enemy.setPosition(Settings.width,y);
            enemys.getChildren().add(enemy);
        }
        if(enemytype==1){
            EnemyBasic enemy = new EnemyBasic(bullets);
            enemy.setPosition(Settings.width,y);
            enemys.getChildren().add(enemy);
        }
        if(enemytype==2){
            TargetEnemy enemy = new TargetEnemy(bullets,Settings.width,y);
            enemys.getChildren().add(enemy);
        }
        if(enemytype==3){
            FollowEnemy enemy = new FollowEnemy(bullets, player);
            enemy.setPosition(Settings.width,y);
            enemys.getChildren().add(enemy);
        }
    }

    private static void spawnThreeUpAndDown(){
        if(!spawnEnemys)return;
        UpAndDownEnemy enemy = new UpAndDownEnemy(bullets);
        enemy.setPosition(Settings.width,1);
        enemys.getChildren().add(enemy);

        enemy = new UpAndDownEnemy(bullets);
        enemy.setPosition(Settings.width+enemy.getWidth(),200);
        enemys.getChildren().add(enemy);

        enemy = new UpAndDownEnemy(bullets);
        enemy.setPosition(Settings.width+enemy.getWidth()*2,400);
        enemys.getChildren().add(enemy);
    }

    private static void spawnMeteor(){
        Random rng = new Random();
        int meteor = rng.nextInt(4);
        int y = rng.nextInt((int)Settings.height-100)+1;
        ArrayList<Image> meteorimage = new ArrayList<>();
        meteorimage.add(ImageLoader.getInstance().getMeteors().get(meteor));
        meteors.getChildren().add(new Meteor(Settings.width,y, meteorimage));
    }

    private static void spawnMotherShip(){
        MotherShip motherShip = new MotherShip(bullets);
        motherShip.setPosition(Settings.width,Settings.height/2-motherShip.getHeight()/2);
        enemys.getChildren().add(motherShip);

        SmallShip smallShip = new SmallShip(bullets,motherShip);
        smallShip.setTheta(Math.toRadians(0));
        smallShip.setPosition(motherShip.getCenterX() - (int) (Math.sin(smallShip.getTheta()) * smallShip.getRadius()), //
                motherShip.getCenterY()- (int) (Math.cos(smallShip.getTheta()) * smallShip.getRadius()));
        enemys.getChildren().add(smallShip);
        smallShip = new SmallShip(bullets,motherShip);
        smallShip.setTheta(Math.toRadians(90));
        smallShip.setPosition(motherShip.getCenterX() - (int) (Math.sin(smallShip.getTheta()) * smallShip.getRadius()), //
                motherShip.getCenterY()- (int) (Math.cos(smallShip.getTheta()) * smallShip.getRadius()));
        enemys.getChildren().add(smallShip);
        smallShip = new SmallShip(bullets,motherShip);
        smallShip.setTheta(Math.toRadians(180));
        smallShip.setPosition(motherShip.getCenterX() - (int) (Math.sin(smallShip.getTheta()) * smallShip.getRadius()), //
                motherShip.getCenterY()- (int) (Math.cos(smallShip.getTheta()) * smallShip.getRadius()));
        enemys.getChildren().add(smallShip);
        smallShip = new SmallShip(bullets,motherShip);
        smallShip.setTheta(Math.toRadians(270));
        smallShip.setPosition(motherShip.getCenterX() - (int) (Math.sin(smallShip.getTheta()) * smallShip.getRadius()), //
                motherShip.getCenterY()- (int) (Math.cos(smallShip.getTheta()) * smallShip.getRadius()));
        enemys.getChildren().add(smallShip);
    }

    private static void spawnSmallShip(){
        SmallShip enemy = new SmallShip(bullets);
        enemy.setPosition(Settings.width,Settings.height/2);
        enemys.getChildren().add(enemy);
    }
}
