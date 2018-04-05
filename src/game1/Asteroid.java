package game1;
import Utilities.GameObject;
import Utilities.SoundManager;
import Utilities.Teleport;
import Utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;


/**
 * Created by kr16264 on 16/01/2018.
 */


public class Asteroid extends GameObject{
    public static final double MAX_SPEED = 100;
    public static Asteroid as1 = null;
    public static Asteroid as2 = null;
    public int radius;
    public static Random  random;
    Image asteroid = Sprite.ASTEROID;



    public Asteroid(double x, double y, double vx, double vy, int radius) {
        super(new Vector2D(x,y),new Vector2D(vx,vy),radius);
        this.radius = radius;


    }


	public static Asteroid makeRandomAsteroid() {
        random = new Random();
        double randx = (random.nextDouble() * 2 - 1 )*Constants.FRAME_WIDTH;
        double randy = (random.nextDouble() * 2 - 1 )*Constants.FRAME_HEIGHT;
        double randvx = (random.nextDouble() * 2 - 1 )*MAX_SPEED;
        double randvy = (random.nextDouble() * 2 - 1 )*MAX_SPEED;
        int randomNum = random.nextInt(2);

        while(true){
            boolean correct = true;

            if(randx - Constants.FRAME_WIDTH/2 < Math.abs(50)){
                randx = (random.nextDouble() * 2 - 1 )*Constants.FRAME_WIDTH;
                correct = false;
            }

            if(randy - Constants.FRAME_WIDTH/2 < Math.abs(50)){
                randy = (random.nextDouble() * 2 - 1 )*Constants.FRAME_WIDTH;
                correct = false;
            }

            if (correct){
                break;
            }
        }

        return new Asteroid(randx,randy,randvx,randvy,Constants.ASTEROID_INITIAL_RADIUS);
    }

    public void hit(){
        this.velocity.reset();
        Game.incScore(500);
        SoundManager.asteroids();
        if(this.radius > Constants.ASTEROID_INITIAL_RADIUS/4){
            as1 = new Asteroid(position.x, position.y,(random.nextDouble() * 2 - 1 )*MAX_SPEED,(random.nextDouble() * 2 - 1 )*MAX_SPEED,this.radius/2);
            as2 = new Asteroid(position.x, position.y,(random.nextDouble() * 2 - 1 )*MAX_SPEED,(random.nextDouble() * 2 - 1 )*MAX_SPEED,this.radius/2);
            Game.asteroids.add(as1);
            Game.asteroids.add(as2);
        }


        dead = true;

    }

    public void update() {
        super.update();
    }


    public boolean canHit(GameObject other){
        if(other.enemy == true){
            return false;
        }else if(other.getClass() == Bullet.class) {
           return true;
        } else if (other.getClass() == Teleport.class){
           return false;
        }
        return false;
    }


    public void draw(Graphics2D g) {
        g.drawImage(asteroid, (int) position.x - radius, (int) position.y - radius, radius * 2, radius * 2, null);
    }

}