package game1;

import Utilities.GameObject;
import Utilities.Vector2D;

import java.awt.*;
import java.util.Random;

/**
 * Created by kairo on 19/03/2018.
 */
public class ForceField extends GameObject {
    Random random = new Random();
    public ForceField(){
        super(new Vector2D(), new Vector2D(),Constants.FORCE_FIELD_RADIUS);
        position = new Vector2D((random.nextDouble() * 2 - 1 )*Constants.FRAME_WIDTH,
                (random.nextDouble() * 2 - 1 )*Constants.FRAME_HEIGHT);
    }

    @Override
    public void collisionHandling(GameObject other) {
        if (this.getClass() != other.getClass() && this.overlap(other)) {
            other.gravity();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.drawOval((int)(position.x - radius), (int)(position.y - radius), (int) (2* radius), (int) (2*radius));
    }
}
