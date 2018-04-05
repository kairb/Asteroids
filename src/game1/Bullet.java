package game1;

import Utilities.GameObject;
import Utilities.Teleport;
import Utilities.Vector2D;

import java.awt.*;

import static game1.Constants.BULLET_RADIUS;
import static game1.Constants.BULLET_TIMEOUT;
import static game1.Constants.SAUCER_BULLET_RADIUS;

/**
 * Created by kr16264 on 13/02/2018.
 */
public class Bullet extends GameObject {
    private long time;
    public Bullet(Vector2D position, Vector2D velocity,boolean enemy){
        super(position,velocity,BULLET_RADIUS);
        dead = false;
        time = System.currentTimeMillis();
        super.enemy = enemy;
        if (enemy){
            super.radius = SAUCER_BULLET_RADIUS;
        }
    }

    public void update(){
        super.update();
        if(System.currentTimeMillis() - time > BULLET_TIMEOUT){
            dead = true;
        }
    }

    public boolean canHit(GameObject other){
        if(other.getClass() == Teleport.class){
            return false;
        }else if (other.getClass() == ForceField.class){
            return false;
        }
        return true;
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        if(enemy){
            g.setColor(Color.RED);
        }
        g.fillOval((int)position.x - BULLET_RADIUS, (int)position.y - BULLET_RADIUS, (int) radius*2, (int)radius*2);
    }
}
