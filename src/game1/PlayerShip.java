package game1;

import Utilities.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;
import static game1.Constants.MUZZLE_VELOCITY;

/**
 * Created by kairo on 02/03/2018.
 */
public class PlayerShip extends Ship{
    private boolean shield = false;
    private int shields = Constants.INITIAL_SHIELDS;            ;
    private long shieldExpiryTime = System.currentTimeMillis();
    private long shieldActivationTime;

    private Image image = Sprite.PLAYERSHIP2;
    private Image shieldImage = Sprite.SHIELD;

    public PlayerShip(Controller ctrl){
        super(ctrl);
    }


    public int getShields(){
        return shields;
    }

    public void masterReset(){
        position = new Vector2D(FRAME_WIDTH/2,FRAME_HEIGHT/2);
        velocity = new Vector2D(0,0);
        direction = new Vector2D(0,-1);
        dead = false;
        SoundManager.extraShip();
        shields = Constants.INITIAL_SHIELDS;
        shield = false;
        shieldExpiryTime = System.currentTimeMillis();
    }

    public boolean shielded(){
        return shield;
    }

    public void incShield(){
        shields++;
    }
    public void activateShield(){
        if(this.ctrl.action().shield && shields > 0 && !shielded()){
            shield = true;
            shieldActivationTime = System.currentTimeMillis();
            shields--;
        }

    }

    public void deactivateShield(){
        shield = false;
    }


    public void update(){
        super.update();
        activateShield();

        if(System.currentTimeMillis() - shieldActivationTime > Constants.SHIELD_EXPIRY_TIME){
            deactivateShield();
        }

    }

    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(position.x,position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);

        g.drawImage(image,(int) ((position.x-position.x) - radius), (int)((position.y- position.y)- radius),(int) radius*2,(int) radius*2,null);
        g.setTransform(at);
        if(shield) {
            g.setColor(Color.cyan);
            g.drawImage(shieldImage, (int) (position.x - radius), (int) (position.y - radius), (int) (radius * 2) +5, (int) (radius * 2)+5, null);

            //g.drawOval((int)(position.x-radius), (int) (position.y - radius), (int)radius*2,(int)radius*2);
        }

    }
    //makes new bullet and adds to game objects
    public void mkBullet(){
        if(System.currentTimeMillis() - timeSinceLast  >= Constants.BULLET_SHOOT_DELAY) {
            bullet = new Bullet(new Vector2D(position), new Vector2D(velocity),false);
            bullet.velocity.addScaled(direction, MUZZLE_VELOCITY);
            bullet.position.addScaled(direction, 35);
            SoundManager.fire();
            timeSinceLast = System.currentTimeMillis();
        }
    }
    //hit method destroys ship if shield is not activated
    public void hit(){
        if(!shield && System.currentTimeMillis() - shieldExpiryTime > Constants.SHIELD_COOLDOWN_TIME){
            dead = true;
            SoundManager.stopThrust();
            Game.decLive();
            SoundManager.play(SoundManager.bangLarge);
            shieldExpiryTime = System.currentTimeMillis();

        }else{
            deactivateShield();
            shieldExpiryTime = System.currentTimeMillis();
        }

    }


    public boolean canHit(GameObject other) {
        if(other.getClass() == Shield.class){
            return false;
        }else if (other.getClass() == Teleport.class){
            return false;
        }else if (other.getClass() == ForceField.class){
            return false;
        }
        return true;
    }
}
