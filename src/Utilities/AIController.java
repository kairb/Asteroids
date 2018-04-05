package Utilities;

import game1.Game;
import game1.PlayerShip;

import java.util.List;
import java.util.Random;

/**
 * Created by kairo on 14/03/2018.
 */
public class AIController implements Controller {
    private final Game game;


    public AIController(Game game) {
        this.game = game;
    }

    @Override
    public Action action() {
        Action action = new Action();

        //aims suacer at playership
        Vector2D aiShipPos =new Vector2D(game.aiShip.position);
        Vector2D playerShipVel = new Vector2D(game.playerShip.velocity);
        Vector2D playerShipPos = new Vector2D(game.playerShip.position);
        game.aiShip.direction = (playerShipPos.subtract(aiShipPos)).add(playerShipVel).normalise();
        //game.aiShip.position.x += 0.5;



        action.shoot = true;
        action.turn = 0;
        action.thrust = 0;
        return action;
    }
}