package game.main;

import game.bullet.BaseBullet;
import game.entitiy.*;
import game.menu.DeathScreen;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.Random;

public class LevelScene extends Scene {

    private static Group root = new Group();
    private static Group meteors = new Group();
    private static Group enemys = new Group();
    private static Group bullets = new Group();
    private static Player player = new Player();
    private static int timer = 0;
    private static final int  resetTimer = 1800;
    private static int difficulty = 1;
    private static int score = 0;
    private static Label scoreLabel = new Label("Score: "+score);
    private static double stageWidth;
    private static double stageHeight;
    private static AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if(timer == resetTimer){
                timer = 0;
                difficulty++;
                spawnMeteor();
                player.getShield();
            }
            timer++;
            if(timer%30 ==0&&!player.isDead()){
                increaseScore(1);
            }

            if(timer%350 ==0&&difficulty>=2){
                spawn();
            }
            if(timer%450 ==0&&timer%900!=0){
                spawn();
            }
            if(timer%900 ==0){
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
                    if (enemy.getCharacterModel().intersects(bullet.getLayoutBounds()) && !enemy.isDead()) {
                        if(!enemy.isInvincible()){
                            enemy.hit();
                            increaseScore(enemy.getPoints());
                        }
                        bulletIterator.remove();
                        continue loop;
                    }
                }
                for (Object o : meteors.getChildren()) {
                    Meteor meteor = (Meteor) o;
                    //if a meteor is hit remove the bullet
                    if (meteor.getCharacterModel().intersects(bullet.getLayoutBounds())) {
                        bulletIterator.remove();
                        continue loop;
                    }
                }

                //if bullet out of screen
                if (!bullet.intersects(-100, -100, stageWidth + stageWidth, stageHeight + 100)) {
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
                if (!enemy.intersects(-100, -100, stageWidth + stageWidth, stageHeight + 100)) {
                    enemyIterator.remove();
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
                //if an enemy is exploded
                if (meteor.isRemove()) {
                    meteorIterator.remove();
                    continue;
                }
                //if enemy out of screen
                if (!meteor.intersects(-100, -100, stageWidth + stageWidth, stageHeight + 100)) {
                    meteorIterator.remove();
                }
            }

            bulletIterator = bullets.getChildren().iterator();
            loop:while (bulletIterator.hasNext())
            {
                BaseBullet bullet = (BaseBullet)bulletIterator.next();
                //if the player is hit
                if(player.getCharacterModel().intersects(bullet.getLayoutBounds())&&!player.isDead()){
                    if(!player.isInvincible())player.hit();
                    bulletIterator.remove();
                    continue;
                }
                //if bullet out of screen
                if(!bullet.intersects(-100,-100,stageWidth+100,stageHeight+100)){
                    bulletIterator.remove();
                    continue;
                }
                for (Object o : meteors.getChildren()) {
                    Meteor meteor = (Meteor) o;
                    //if a meteor is hit remove the bullet
                    if (meteor.getCharacterModel().intersects(bullet.getLayoutBounds())) {
                        bulletIterator.remove();
                        continue loop;
                    }
                }
            }


            if(player.isRemove()){
                animationTimer.stop();
                //gameover change scene to gameOverScene
                DeathScreen.getInstance(stageWidth,stageHeight,gameStage).setScore(score);
                gameStage.setScene(DeathScreen.getInstance(stageWidth,stageHeight,gameStage));
            }
        }
    };
    private static Stage gameStage;
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

    private LevelScene(Parent root, double width, double height) {
        super(root, width, height);
    }


    public static LevelScene getInstance( double width, double height,Stage stage){
        if(instance==null){
            gameStage = stage;
            stageWidth = width;
            stageHeight = height;instance = new LevelScene(createGUI(stageWidth, stageHeight), stageWidth, stageHeight);
            instance.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP: case W:   player.setUp(true);break;
                    case DOWN: case S:  player.setDown(true); break;
                    case LEFT: case A:  player.setLeft(true); break;
                    case RIGHT: case D: player.setRight(true); break;
                    case R: restartLevel(); break;
                    case SPACE:  player.setShooting(true); break;
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
            animationTimer.start();
        }
        return instance;

    }

    private static Group createGUI(double width, double height) {
        Image backgroundImage = new Image("Background/background.jpg",width,width,true,false);
        ImageView backgroundImageView = new ImageView(backgroundImage);
        root.setStyle(menuStyle);
        root.getChildren().addAll(backgroundImageView,scoreLabel,player,enemys,bullets,meteors);
        player.setPosition(0,(stageHeight-player.getHeight())/2);
        spawn();
        spawnMeteor();
        return root;
    }

    public static void restartLevel(){
        enemys = new Group();
        bullets = new Group();
        player = new Player();
        meteors = new Group();
        score = 0;
        scoreLabel = new Label("Score: "+score);
        timer = 0;
        difficulty = 1;
        createGUI(stageWidth,stageHeight);
        animationTimer.start();
    }

    private static void spawn(){
        //randomize the enemy and his startpos
        Random rng = new Random();
        int enemytype = rng.nextInt(4);
        int y = rng.nextInt((int)stageHeight-100)+1;
        //enemytype = 3;
        if(enemytype==0){
            UpAndDownEnemy enemy = new UpAndDownEnemy(bullets);
            enemy.setPosition(stageWidth,y);
            enemys.getChildren().add(enemy);
        }
        if(enemytype==1){
            EnemyBasic enemy = new EnemyBasic(bullets);
            enemy.setPosition(stageWidth,y);
            enemys.getChildren().add(enemy);
        }
        if(enemytype==2){
            TargetEnemy enemy = new TargetEnemy(bullets,stageWidth,y);
            enemys.getChildren().add(enemy);
        }
        if(enemytype==3){
            FollowEnemy enemy = new FollowEnemy(bullets, player);
            enemy.setPosition(stageWidth,y);
            enemys.getChildren().add(enemy);
        }
    }

    private static void spawnThreeUpAndDown(){
        UpAndDownEnemy enemy = new UpAndDownEnemy(bullets);
        enemy.setPosition(stageWidth,1);
        enemys.getChildren().add(enemy);

        enemy = new UpAndDownEnemy(bullets);
        enemy.setPosition(stageWidth+enemy.getWidth(),200);
        enemys.getChildren().add(enemy);

        enemy = new UpAndDownEnemy(bullets);
        enemy.setPosition(stageWidth+enemy.getWidth()*2,400);
        enemys.getChildren().add(enemy);
    }

    private static void spawnMeteor(){
        Random rng = new Random();
        int enemytype = rng.nextInt(4);
        int y = rng.nextInt((int)stageHeight-100)+1;
        String imageURL = "Aestroids/aestroid_brown.png";
        if(enemytype==0)imageURL = "Aestroids/aestroid_brown";
        if(enemytype==1)imageURL = "Aestroids/aestroid_dark";
        if(enemytype==2)imageURL = "Aestroids/aestroid_gay_2";
        if(enemytype==3)imageURL = "Aestroids/aestroid_gray";
        meteors.getChildren().add(new Meteor(stageWidth,y,imageURL));
    }
}
