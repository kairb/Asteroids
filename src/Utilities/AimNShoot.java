package Utilities;

import game1.Game;

import java.util.List;

/**
 * Created by kairo on 27/02/2018.
 */
public class AimNShoot implements Controller {
    Action action = new Action();
    List<GameObject> objects;
    Vector2D shipPos;
    Vector2D closest;
    Vector2D closestVel;
    @Override
    public Action action() {
        shipPos = new Vector2D(Game.playerShip.position);
        objects = Game.getGameObjects();
        double distance = Double.MAX_VALUE;
        for (GameObject i: objects ){
            if (i.getClass() == game1.Asteroid.class){
                if(shipPos.dist(i.position) < distance){
                    distance = shipPos.dist(i.position);
                    closest = new Vector2D(i.position);
                    closestVel = new Vector2D(i.velocity);
                }
            }
        }

        Game.playerShip.direction = (closest.subtract(shipPos)).normalise();

        action.shoot = true;
        action.turn = 0;
        action.thrust = 0;
        return action;
    } }