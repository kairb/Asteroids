package game1;

import Utilities.GameObject;
import Utilities.GameOver;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by kr16264 on 16/01/2018.
 */
public class View extends JComponent {

    private Game game;
    Image im = Sprite.BACKGROUND;
    AffineTransform bgTransf;
    public static GameOver gameOver = new GameOver();

    public View(Game game) {
        this.game = game;

        double imWidth = im.getWidth(null);
        double imHeight = im.getHeight(null);
        double stretchx = (imWidth > Constants.FRAME_WIDTH? 1 :
                Constants.FRAME_WIDTH/imWidth);
        double stretchy = (imHeight > Constants.FRAME_HEIGHT? 1 :
                Constants.FRAME_HEIGHT/imHeight);
        bgTransf = new AffineTransform();
        bgTransf.scale(stretchx, stretchy);
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        // paint the background
        if(!gameOver.getGameState()){
            g.drawImage(im, bgTransf,null);
            synchronized (Game.class) {
                for (GameObject object : game.objects)
                    object.draw(g);
            }
            g.setColor(Color.red);
            g.scale(2,2);
            g.drawString("Score: " + Integer.toString(game.getScore()) + " Lives: " + Integer.toString(game.getLives()) +
                    " Level: " + Integer.toString(game.getLevel()) + " Shields: " + Integer.toString(game.playerShip.getShields()), 5,10);
        }
        gameOver.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}