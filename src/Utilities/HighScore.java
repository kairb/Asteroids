package Utilities;

import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by kr16264 on 08/03/2018.
 */
public class HighScore {
    private String fileName = "scores.txt";
    private Scanner in;
    private BufferedWriter bufferedWriter = null;
    public HighScore(){

        //trys to open fileName. If doesnt exist, creates fileName and proceeds to open after

    }
    public ArrayList<Pair<String, Integer>> gethighScores(){
        String scores = "";
        try{
            in = new Scanner(new File(fileName));
        }catch (Exception e){
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.close();

            }catch (Exception e1){
                System.out.println(e1);
            }
        }finally{
            try{
                in = new Scanner(new File(fileName));
            }catch (Exception e){
                System.out.println(e);
            }
        }

        ArrayList<Pair<String, Integer>> scoreList = new ArrayList<>();

        while (in.hasNextLine()){
            scoreList.add(new Pair<String, Integer>(in.nextLine(),Integer.valueOf(in.nextLine())));
        }
        Collections.sort(scoreList, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        return scoreList;
    }

    public void add(String name,int score){
        try{
            bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
            bufferedWriter.write(name + "\n" + Integer.toString(score));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (Exception e1) {
        }finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception ioe2) {
                }
            }
        }
    }
}
