package Utilities;

import game1.Game;
import game1.View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by kairo on 01/02/2018.
 */
public class Keys extends KeyAdapter implements Controller {
    Action action;
    public Keys() {
        action = new Action();
    }

    public Action action() {
        // this is defined to comply with the standard interface
        return action;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 1;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = -1;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = +1;
                break;
            case KeyEvent.VK_SPACE:
                action.shoot = true;
                break;
            case KeyEvent.VK_SHIFT:
                action.shield = true;
                break;
            case KeyEvent.VK_R:
                View.gameOver.hide();
                Game.reset();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        // please add appropriate event handling code
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 0;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = 0;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = 0;
                break;
            case KeyEvent.VK_SPACE:
                action.shoot = false;
                break;
            case KeyEvent.VK_SHIFT:
                action.shield = false;
                break;
        }
    }
}