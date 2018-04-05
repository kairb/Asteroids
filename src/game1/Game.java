package game1;

import Utilities.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kr16264 on 16/01/2018.
 */
public class Game {
    public static List<GameObject> objects;
    public Keys controller;
    public static PlayerShip playerShip;
    public static Saucer aiShip;
    public static int lives = Constants.INITIAL_LIVES;
    public static int score = 0;
    public static int newLife = 10000;
    public static int level = Constants.INITIAL_LEVEL;
    public static List<GameObject> asteroids = new ArrayList<>();
    public static long levelStartTime;
    public static LevelGenerator levelGenerator;

    public Game(){
        objects = new ArrayList<GameObject>();
        levelGenerator = new LevelGenerator(this);
        controller = new Keys();
        levelGenerator.genLevel();

        aiShip = new Saucer(new AIController(this));
        /*objects.add(aiShip);

        Shield shield = new Shield();
        objects.add(shield);*/
        playerShip = new PlayerShip(controller);
        objects.add(playerShip);
        levelStartTime = currentTime();
        SoundManager.gameStart();

    }
    //increments score. adds new life after every 10k points scored
    public static void incScore(int inc){
        score += inc;
        newLife -= inc;
        if(newLife <=0){
            incLive();
            newLife = 10000;
        }
    }
    public static List<GameObject> getGameObjects(){return objects;}

    public static void incLive(){
        lives +=1;
    }

    public static void decLive(){
        lives -= 1;
    }

    public static int getScore(){
        return score;
    }

    public static int getLives(){
        return lives;
    }

    public static int getLevel(){return level;}


    public static void incLevel(){
        level += 1;
        levelStartTime = currentTime();
        SoundManager.levelUp();
    }
    public static void resetLevel(){

    }

    public static long currentTime(){
        return System.currentTimeMillis();
    }

    public boolean has(Class type ){
        for (GameObject o: objects){
            if(o.getClass() == type){
                return true;
            }
        }
        return false;
    }

    public static void reset(){
        synchronized (Game.class){
            objects.clear();
        }
        aiShip.dead = true;
        level = Constants.INITIAL_LEVEL;
        score = 0;
        lives = Constants.INITIAL_LIVES;
        newLife = 10000;
        levelStartTime = currentTime();
        playerShip.masterReset();
        objects.add(playerShip);
        levelGenerator.genLevel();
        SoundManager.gameStart();
    }



    public static void main(String[] args) throws Exception {
        Game game = new Game();
        View view = new View(game);
        new JEasyFrame(view, "Asteroids- Kai Roper-Blackman").addKeyListener(game.controller);
        while (true) {
            game.update();
            view.repaint();
            Thread.sleep(Constants.DELAY);
        }
    }

    public void update() {
        if(getLives() < 1 && !View.gameOver.getGameState()){
            SoundManager.gameOver();
            View.gameOver.display();
            //objects.clear();
        }

        //level construction

        //checks for in game collisions
        for (GameObject a : objects) {
            for (GameObject b : objects) {
                a.collisionHandling(b);
            }
        }

        //creates alive list of objects and new playerShip bullets
        List<GameObject> alive = new ArrayList<>();
        for (GameObject a : objects) {
            if(!a.dead){
                a.update();
                alive.add(a);
                if(playerShip.bullet != null){
                    alive.add(playerShip.bullet);
                    playerShip.bullet = null;
                }

                if(aiShip.bullet != null){
                    alive.add(aiShip.bullet);
                    aiShip.bullet = null;
                }
            }
        }

        if (playerShip.dead){
            if (getLives()> 0){
                playerShip.reset();
                alive.add(playerShip);
            }
        }

        //adds split asteroids to list
        alive.addAll(asteroids);
        asteroids.clear();

        //adds alive list of to game objects list synchronized with paint component
        synchronized (Game.class) {
            objects.clear();
            objects.addAll(alive);
        }

        levelGenerator.updateLevel();


        if(!has(Asteroid.class ) && aiShip.dead){
            playerShip.reset();
            synchronized (Game.class){
                objects.clear();
                objects.add(playerShip);
                levelGenerator.genLevel();

            }
        }

    }
}