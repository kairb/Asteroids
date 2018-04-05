package game1;

import java.awt.*;

/**
 * Created by kr16264 on 16/01/2018.
 */
public class Constants {
    public static final int N_INITIAL_ASTEROIDS = 1;


    public static Dimension FRAME_SIZE =
            new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    public static final int FRAME_HEIGHT = 480*2;
    public static final int FRAME_WIDTH =640*2;


    public static final int DELAY = 20;  // in milliseconds
    public static final double DT = DELAY / 1000.0;  // in seconds
    public static final Color BG_COLOR = Color.black;
    public static final int BULLET_RADIUS = 3;
    public static final int MUZZLE_VELOCITY = 300;
    public static final int SAUCER_MUZZLE_VELOCITY = 300;

    public static final int MUZZLE_VELOCITY_SAUCER = 300;
    public static final long BULLET_TIMEOUT = 5000;
    public static final int TREASURE_RADIUS = 20;
    public static final int TREASURE_TIMEOUT = 15000;
    public static final int SHIELD_REGEN_TIME = 20000;
    public static final int SHIELD_COOLDOWN_TIME = 750;
    public static final int TREASURE_SPAWN_INTERVAL= 30000;
    public static final int TELEPORT_RADIUS = 20;
    public static final int BULLET_SHOOT_DELAY = 0;
    public static final int SHIELD_EXPIRY_TIME = 10000;
    public static final int PLAYERSSHIP_RADIUS = 30;
    public static final int SAUCER_RADIUS = 60;
    public static final int SAUCER_BULLET_DELAY = 2500;
    public static final int ASTEROID_INITIAL_RADIUS = 100;
    public static final int INITIAL_LIVES = 3;
    public static final int INITIAL_LEVEL= 1;
    public static final int SAUCER_BULLET_RADIUS = 10;
    public static final int INITIAL_SHIELDS = 1;
    public static final int FORCE_FIELD_RADIUS = 100;




}