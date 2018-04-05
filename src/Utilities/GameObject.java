package Utilities;

import game1.Constants;

import java.awt.*;
import java.util.Random;

import static game1.Constants.*;

/**
 * Created by kr16264 on 12/02/2018.
 */
public abstract class GameObject{
    public Vector2D position;
    public Vector2D velocity;
    public boolean dead;
    public double radius;
    public boolean enemy = false;
    //ForceFieldGravity field;
    Random random = new Random();
    private Vector2D gravityForce = new Vector2D((random.nextDouble() * 2 - 1 )*300,(random.nextDouble() * 2 - 1 )* 300);


    public GameObject(Vector2D position, Vector2D velocity, double radius) {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.dead = false;
    }

    public void hit(){
        dead = true;
    }

    public void update(){
        //field.update(this,Constants.DT);
        position.addScaled(velocity,DT);
        position.wrap(FRAME_WIDTH,FRAME_HEIGHT);
    }

    public boolean overlap(GameObject other){
        return this.position.dist(other.position) < (this.radius + other.radius);

    }
    public void collisionHandling(GameObject other) {
        if (this.getClass() != other.getClass() && this.overlap(other) && this.canHit(other)) {
            this.hit();
        }
    }


    public  boolean canHit(GameObject other){
        return true;
    }

    public void teleport(){
        position.x = (random.nextDouble() * 2 - 1 )*Constants.FRAME_WIDTH;
        position.y = (random.nextDouble() * 2 - 1 )* Constants.FRAME_HEIGHT;
    }

    public void gravity() {
        double direction = position.x > FRAME_WIDTH / 2 ? 1 : -1;
        velocity.addScaled(gravityForce, direction * DT);
    }

    public abstract void draw(Graphics2D g);
}
