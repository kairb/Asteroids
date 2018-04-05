package Utilities;

/**
 * Created by kr16264 on 27/02/2018.
 */
public class RotateNShoot implements Controller {
    Action action = new Action();
    @Override
    public Action action() {
        action.shoot = true;
        action.turn = 1;
        action.thrust = 0;
        return action;
    } }