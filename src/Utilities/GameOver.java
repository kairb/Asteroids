package Utilities;

import game1.Constants;
import game1.Game;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kr16264 on 08/03/2018.
 */
public class GameOver{
    private HighScore highScores = new HighScore();
    private ArrayList<Pair<String, Integer>> scores;
    private boolean status;

    public GameOver(){
        status = false;
    }

    public boolean getGameState(){
        return status;
    }

    public void display(){
        String name = JOptionPane.showInputDialog("What is your name?");
        if(name == null){
            name = "Anonymous";
        }
        status = true;
        highScores.add(name,Game.getScore());
        scores = highScores.gethighScores();
    }

    public void hide() {
        status = false;
    }


    public void draw(Graphics2D g){
        if (status){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman",Font.PLAIN,30));
            int pos = 40;
            int inc = 40;
            g.drawString("Game Over. \"r\" to restart", Constants.FRAME_WIDTH/2, Constants.FRAME_HEIGHT/2);

            g.drawString("HighScores:",Constants.FRAME_WIDTH/2, (Constants.FRAME_HEIGHT/2)+pos);
            pos +=inc;
            for(int i = 0; i < scores.size();i++){
                g.drawString(Integer.toString(i+1) +". "  +scores.get(i).getKey()+ " " + String.valueOf(scores.get(i).getValue()),
                        Constants.FRAME_WIDTH/2,(Constants.FRAME_HEIGHT/2)+pos);
                pos+= inc;
            }

        }

    }
}
