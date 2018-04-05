package game1;

import Utilities.GameObject;
import Utilities.Vector2D;
import jdk.nashorn.internal.ir.SplitReturn;

import java.awt.*;
import java.util.Random;

import static game1.Asteroid.MAX_SPEED;
import static game1.Constants.PLAYERSSHIP_RADIUS;
import static game1.Constants.TREASURE_RADIUS;

/**
 * Created by kr16264 on 06/03/2018.
 */
public class Shield extends GameObject {
    private Color[] colors = {Color.RED,Color.GREEN,Color.BLUE};
    private long time;
    private Image image = Sprite.SHIELD;
    Random random = new Random();

    public Shield() {
        super(new Vector2D(), new Vector2D(0, 0), PLAYERSSHIP_RADIUS);
        position.x = (random.nextDouble() * 2 - 1) * Constants.FRAME_WIDTH;
        position.y = (random.nextDouble() * 2 - 1) * Constants.FRAME_HEIGHT;
        velocity.x = (random.nextDouble() * 2 - 1 )*MAX_SPEED/2;
        velocity.y = (random.nextDouble() * 2 - 1 )*MAX_SPEED/2;
        time = System.currentTimeMillis();

    }

    public void draw(Graphics2D g){
        g.drawImage(image, (int) (position.x - radius), (int) (position.y - radius), (int) radius * 2, (int) radius * 2, null);
        g.setColor(Color.cyan);
        g.drawString("+1",(int)position.x - 6, (int)position.y + 5);
        /*g.setColor(colors[random.nextInt(colors.length)]);
        g.fillOval((int) (position.x - radius), (int) (position.y - radius) ,(int)radius ,(int) radius);*/
    }

    public void update(){
        super.update();
        if(System.currentTimeMillis() - time > Constants.TREASURE_TIMEOUT){
            dead = true;
        }

    }

    /*public void collisionHandling(GameObject other){
        if(other.getClass() == PlayerShip.class && this.overlap(other)){
            this.hit();
        }
    }*/

    public boolean canHit(GameObject other){
        if(other.getClass() == Bullet.class){
            return true;
        }else if (other.getClass() == PlayerShip.class){
            return true;
        }
        return false;
    }

    public void hit(){
        Game.incScore(5000);
        dead = true;
        Game.playerShip.incShield();
        Game.levelStartTime = Game.currentTime();
    }
}
