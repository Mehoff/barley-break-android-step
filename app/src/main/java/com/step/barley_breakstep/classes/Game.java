package com.step.barley_breakstep.classes;

import android.widget.TextView;

import java.util.Random;

public class Game {

    private int[][] _field = new int[4][4];
    private TextView[][] _textViews = new TextView[4][4];

    public Game(TextView[] textViews) throws Exception {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                _textViews[i][j] = textViews[i + j];
            }
        }
    }

    public void init(){
        this.shuffleField();
    }

    private void setViewsTextToFieldValues(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                _textViews[i][j].setText(_field[i][j] + "");
            }
        }
    }

    private void shuffleField(){
        shuffle(_field);
        setViewsTextToFieldValues();
    }

    void shuffle(int[][] a) {
        Random random = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                int temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
    }
}
