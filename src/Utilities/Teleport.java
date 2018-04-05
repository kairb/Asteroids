package Utilities;

import game1.Bullet;
import game1.Constants;
import game1.Game;

import java.awt.*;
import java.util.Random;


/**
 * Created by kairo on 13/03/2018.
 */
public class Teleport extends GameObject{
    Random random = new Random();
    public Teleport(){
        super(new Vector2D(), new Vector2D(), Constants.TELEPORT_RADIUS);
        position.x = (random.nextDouble() * 2 - 1 )*Constants.FRAME_WIDTH;
        position.y = (random.nextDouble() * 2 - 1 )*Constants.FRAME_HEIGHT;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.white);
        for (int i = (int) radius*2; i >= 0; i-=10){
            g.drawOval((int)(position.x - i/2), (int) (position.y - i/2), i, i);
        }

    }

    public boolean canHit(GameObject other){
        if (other.getClass() == Bullet.class){
            return false;
        }
        return true;
    }

    public void collisionHandling(GameObject other){
        if (this.getClass() != other.getClass() && this.overlap(other) && this.canHit(other)){
            other.teleport();
        }
    }
}
