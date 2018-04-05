package Utilities;

import game1.*;

import java.util.Random;

/**
 * Created by kr16264 on 08/03/2018.
 */
public class LevelGenerator {
    Game game;
    private int treasureGiven = 0;
    private long lastUpdate = 0;
    private boolean saucerOccured = false;

    public LevelGenerator(Game game){
        this.game = game;
    }

    //generates new level
    public void genLevel(){
        synchronized (Game.class) {
            for (int i = 0; i < game.getLevel() + Constants.N_INITIAL_ASTEROIDS; i++) {
                game.objects.add(Asteroid.makeRandomAsteroid());
                if(game.getLevel() % 5 == 0){
                }
            }

            //game.objects.add(game.playerShip);
            game.objects.add(new ForceField());
            game.objects.add(new Teleport());
            treasureGiven = 0;
            lastUpdate = System.currentTimeMillis();
            saucerOccured = false;
        }
    }

    //updates level by adding Shield, teleporters and ai to game at random intervals less
    public void updateLevel(){

        if(!(game.playerShip.shielded())&& game.playerShip.getShields()<=4 && treasureGiven <= game.getLevel()){
            if (System.currentTimeMillis() - lastUpdate> Constants.TREASURE_SPAWN_INTERVAL && !game.has(Shield.class)){
                synchronized (Game.class){
                    game.objects.add(new Shield());
                }
                treasureGiven ++;
                lastUpdate = System.currentTimeMillis();
            }

        }
        bossStage();
    }

    public void bossStage(){
        synchronized (Game.class){
            if(!game.has(Asteroid.class) && !saucerOccured){
                game.aiShip = new Saucer(new AIController(game));
                game.objects.add(game.aiShip);
                game.aiShip.spawn();
                saucerOccured = true;
            }

        }
    }
}
