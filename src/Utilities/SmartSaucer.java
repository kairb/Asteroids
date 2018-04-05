package Utilities;

import game1.Game;
import game1.PlayerShip;

import java.util.List;

/**
 * Created by kairo on 27/02/2018.
 */
public class SmartSaucer implements Controller {
    private final Game game;

    public SmartSaucer(Game game) {
        this.game = game;
    }

    @Override
    public Action action() {
        Action action = new Action();
        List<GameObject> objects = game.getGameObjects();

        Vector2D shipPos;
        Vector2D closest = null;
        shipPos = new Vector2D(Game.aiShip.position);
        PlayerShip ship = null;
        double distance = Double.MAX_VALUE;
        for (GameObject i : objects) {
            if (i.getClass() == game1.PlayerShip.class) {
                if (shipPos.dist(i.position) < distance) {
                    if (i instanceof PlayerShip) {
                        ship = (PlayerShip) i;
                    }
                    distance = shipPos.dist(i.position);
                    closest = new Vector2D(i.position);
                }
            }
        }
        if (closest == null) return new Action();
        ship.direction = (closest.subtract(shipPos)).normalise();

        action.shoot = true;
        action.turn = 0;
        action.thrust = 0;
        return action;
    }
}