package com.step.barley_breakstep.classes;

import android.os.Debug;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class Game {

    private final int SIZE = 4 * 4;
    private final String TAG = "MyApp";
    private int[] _field;
    private TextView[] _textViews;

    public Game(TextView[] textViews) throws Exception {
        if(textViews.length != SIZE){
            throw new Exception("Text views array length must be size of: " + SIZE + ", got: " + textViews.length);
        }
        _textViews = textViews;
        _field = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 ,8 ,9, 10, 11, 12 ,13, 14, 15 };
    }

    public void init(){
        this.shuffleField();
    }

    private void setViewsTextToFieldValues(){
        for(int i = 0; i < SIZE; i++){
            if(_field[i] == 0){
                _textViews[i].setText(" ");
            } else {
            _textViews[i].setText(_field[i] + "");
            }
        }
    }
    
    private void shuffleField(){
        setViewsTextToFieldValues();
    }

    private void shuffle(int[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }


}
