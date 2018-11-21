package game.main;

import game.Bullet.BaseBullet;
import game.entitiy.EnemyBasic;
import game.entitiy.Player;
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

public class LevelScene extends Scene {

    private static Group root = new Group();
    private static Group enemys = new Group();
    private static Group bullets = new Group();
    private static Player player = new Player();
    private static int timer = 0;
    private static final int  resetTimer = 3600;
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
            }
            timer++;
            if(timer%30 ==0&&!player.isDead()){
                increaseScore(1);
            }

            if(timer%450 ==0){
                spawn();
            }

            player.updatePlayer();
            for(Node enemy:enemys.getChildren()){
                ((EnemyBasic)enemy).update();
            }
            moveBullets();
            //check the position of all the bullets
            Iterator bulletIterator = player.getBullets().getChildren().iterator();
            while (bulletIterator.hasNext())
            {
                BaseBullet bullet = (BaseBullet)bulletIterator.next();
                Iterator enemyIterator = enemys.getChildren().iterator();
                while (enemyIterator.hasNext())
                {
                     EnemyBasic enemy = (EnemyBasic)enemyIterator.next();
                     //if an enemy is hit
                     if(enemy.getCharacterModel().intersects(bullet.getLayoutBounds())&&!enemy.isDead()){
                         enemy.die();
                         increaseScore(enemy.getPoints());
                         bulletIterator.remove();
                     }
                     //if bullet out of screen
                    if(!bullet.intersects(-100,-100,stageWidth+100,stageHeight+100)){
                        bulletIterator.remove();
                    }
                }
            }
            Iterator enemyIterator = enemys.getChildren().iterator();
            while (enemyIterator.hasNext())
            {

                EnemyBasic enemy = (EnemyBasic)enemyIterator.next();
                //if the player collides
                if(player.getCharacterModel().intersects(enemy.getLayoutBounds())&&!player.isDead()&&!enemy.isDead()){
                    player.die();
                    enemy.die();
                }
                //if an enemy is exploded
                if(enemy.isRemove()){
                    enemyIterator.remove();
                }
                //if enemy out of screen
                if(!enemy.intersects(-100,-100,stageWidth+100,stageHeight+100)){
                    enemyIterator.remove();
                }
            }
            bulletIterator = bullets.getChildren().iterator();
            while (bulletIterator.hasNext())
            {
                BaseBullet bullet = (BaseBullet)bulletIterator.next();
                //if the player is hit
                if(player.getCharacterModel().intersects(bullet.getLayoutBounds())&&!player.isDead()){
                    player.die();
                    bulletIterator.remove();
                }
                //if bullet out of screen
                if(!bullet.intersects(-100,-100,stageWidth+100,stageHeight+100)){
                    bulletIterator.remove();
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

    public LevelScene(Parent root, double width, double height) {
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
        root.getChildren().addAll(backgroundImageView,player,enemys, scoreLabel,bullets);
        player.setPosition(0,(stageHeight-player.getHeight())/2);
        spawn();
        return root;
    }

    public static void restartLevel(){
        enemys = new Group();
        bullets = new Group();
        player = new Player();
        score = 0;
        scoreLabel = new Label("Score: "+score);
        timer = 0;
        difficulty = 1;
        createGUI(stageWidth,stageHeight);
        animationTimer.start();
    }

    private static void spawn(){
        EnemyBasic enemy = new EnemyBasic(bullets);
        enemy.setPosition(stageWidth,(stageHeight-enemy.getHeight())/2);
        enemys.getChildren().add(enemy);
    }
}
