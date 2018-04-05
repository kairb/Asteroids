package game1;

import Utilities.Controller;
import Utilities.GameObject;
import Utilities.SoundManager;
import Utilities.Vector2D;

import java.awt.*;

import static game1.Constants.*;

public abstract class Ship extends GameObject{
    public int[] XP = {0,-5,0,5};
    public int[] YP = {-10,5,0,5};
    public int[] XPTHRUST = {0,-4,0,4};
    public int[] YPTHRUST = {1,5,15,5};
    public Bullet bullet = null;
    public long timeSinceLast = System.currentTimeMillis();
    public Vector2D direction;



    // rotation velocity in radians per second
    public static final double STEER_RATE = 1.5 * Math.PI;

    // acceleration when thrust is applied
    public static final double MAG_ACC = 300;

    // constant speed loss factor
    public static final double DRAG = 0.0;

    public static final int DRAWING_SCALE = 3;

    public static final Color COLOR = Color.cyan;

    // direction in which the nose of the playerShip is pointing
    // this will be the direction in which thrust is applied
    // it is a unit vector representing the angle by which the playerShip has rotated


    // controller which provides an Action object in each frame
    public Controller ctrl;


    public Ship(Controller ctrl) {
        super(new Vector2D(FRAME_WIDTH/2,FRAME_HEIGHT/2),new Vector2D(0,0),Constants.PLAYERSSHIP_RADIUS);
        this.ctrl = ctrl;
        direction = new Vector2D(0,-1);
    }

    public void hit(){
        dead = true;
        Game.decLive();
        SoundManager.play(SoundManager.bangLarge);
    }

    public void update() {
        direction.rotate(ctrl.action().turn * STEER_RATE * DT);
        velocity.addScaled(direction,ctrl.action().thrust * MAG_ACC * DT);
        velocity.subtract((velocity.x == 0) ? 0:(velocity.x > 0 )? DRAG: -DRAG,
                (velocity.y == 0) ? 0:(velocity.y > 0 )? DRAG: -DRAG);

        super.update();
        if(ctrl.action().thrust == 1){
            SoundManager.startThrust();
        }else{
            SoundManager.stopThrust();
        }
        if (ctrl.action().shoot) {
            mkBullet();
            ctrl.action().shoot = false;
        }
    }

    public void reset(){
        position = new Vector2D(FRAME_WIDTH/2,FRAME_HEIGHT/2);
        velocity = new Vector2D(0,0);
        direction = new Vector2D(0,-1);
        dead = false;
        SoundManager.extraShip();
    }



    public abstract void mkBullet();

    public abstract void draw(Graphics2D g);
}