package game1;

import Utilities.Controller;
import Utilities.GameObject;
import Utilities.SoundManager;
import Utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

import static game1.Asteroid.MAX_SPEED;
import static game1.Constants.FRAME_WIDTH;
import static game1.Constants.MUZZLE_VELOCITY;
import static game1.Constants.SAUCER_BULLET_DELAY;

/**
 * Created by kairo on 02/03/2018.
 */
public class Saucer extends Ship {
    private final int MAX_HEALTH_BAR_WIDTH = 102;//so 100 health can be displayed accurately
    private final int MAX_HEALTH_BAR_HEIGHT = 10;
    private Image image = Sprite.SAUCER;
    private Random random = new Random();
    private int health = 100;
    private int healthDeduction;
    public Saucer(Controller ctrl){
        super(ctrl);
        super.enemy = true;
        position = new Vector2D((random.nextDouble() * 2 - 1 )*Constants.FRAME_WIDTH,(random.nextDouble() * 2 - 1 )*Constants.FRAME_HEIGHT);
        double randx = (random.nextDouble() * 2 - 1 )*Constants.FRAME_WIDTH;
        double randy = (random.nextDouble() * 2 - 1 )*Constants.FRAME_HEIGHT;
        super.radius = Constants.SAUCER_RADIUS;
        velocity = new Vector2D((random.nextDouble() * 2 - 1 )*MAX_SPEED,(random.nextDouble() * 2 - 1 )*MAX_SPEED);
        healthDeduction = health/ Game.getLevel();
    }

    public void spawn(){
        SoundManager.saucerSpawn();

    }

    public void draw(Graphics2D g) {
        g.drawImage(image,(int) (position.x-radius),(int) (position.y-radius), (int) radius*2, (int) radius *2,null);
        g.setColor(Color.WHITE);
        g.drawRect((int) (position.x- radius+10),(int) (position.y+radius-20), MAX_HEALTH_BAR_WIDTH, MAX_HEALTH_BAR_HEIGHT);
        g.setColor(Color.RED);
        g.fillRect((int) (position.x- radius+11),(int) (position.y+radius-19),MAX_HEALTH_BAR_WIDTH - (100- health)-1, MAX_HEALTH_BAR_HEIGHT - 1);
    }
    public void mkBullet(){
        if(System.currentTimeMillis() - timeSinceLast  >= SAUCER_BULLET_DELAY) {
            bullet = new Bullet(new Vector2D(position), new Vector2D(velocity),true);
            bullet.velocity.addScaled(direction, Constants.SAUCER_MUZZLE_VELOCITY);
            bullet.position.addScaled(direction, radius + Constants.SAUCER_BULLET_RADIUS + 1);
            SoundManager.play(SoundManager.beat1);
            timeSinceLast = System.currentTimeMillis();
        }
    }
    /*public void update(){
        super.update();
        if(position.x - (radius + 10) < 0 || position.x + (radius + 10) > Constants.FRAME_WIDTH){
            velocity.flip();
        }else if(position.y - (radius + 10) < 0 || position.x + (radius + 10) > Constants.FRAME_HEIGHT){
            velocity.flip();
        }
    }*/


    public void hit(){
        health -= healthDeduction;
        if(health < 2){
            dead = true;
            SoundManager.play(SoundManager.bangLarge);
            Game.incLevel();
            Game.incScore(Game.getLevel() * 750);
        }
    }

    public boolean canHit(GameObject other){
        if (other.getClass() == Bullet.class && !other.enemy){
            return true;
        }
        return false;
    }

}
