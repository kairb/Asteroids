package Utilities;

import java.util.Random;

/**
 * Created by kr16264 on 27/02/2018.
 */
public class RandomAction implements Controller {
    Action action = new Action();
    public int timeTillRotateChange = 0;
    public int timeTillThrustChange = 0;
    public Random random = new Random();
    @Override

    public Action action() {
        timeTillRotateChange--;
        timeTillThrustChange--;

        int shoot = random.nextInt(2);
        if (shoot == 1){
            action.shoot = true;
        }else{
            action.shoot = false;
        }

        if(timeTillRotateChange <= 0){
            action.turn = random.nextInt(3) - 1;
            timeTillRotateChange = 40 + random.nextInt(20);
        }

        if (timeTillThrustChange <= 0){
            action.thrust = random.nextInt(2);
            timeTillThrustChange = 40 + random.nextInt(20);
        }



        return action;
    } }