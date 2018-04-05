package Utilities;
import Utilities.Vector2D;
import static game1.Constants.FRAME_WIDTH;

final public class ForceFieldGravity implements ForceFieldInterface{

    final static Vector2D gravityForce=new Vector2D(0,30);
    final static double maxFallSpeed=10;

    public void update(GameObject obj, double DT){
        double direction = obj.position.x>FRAME_WIDTH/2 ?1:-1;

        obj.velocity.addScaled(gravityForce,direction*DT);
//			System.out.println("direrction*DT: "+direrction*DT*gravityForce.mag());

    }

}