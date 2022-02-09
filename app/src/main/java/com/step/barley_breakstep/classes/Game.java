package com.step.barley_breakstep.classes;

import android.os.Debug;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.step.barley_breakstep.MainActivity;

import java.util.Arrays;
import java.util.Random;

public class Game {

    private final int BORDER_SIZE = 4;
    private final int SIZE = BORDER_SIZE * BORDER_SIZE;
    private final String TAG = "MyApp";
    private int[] _field;
    private int _cellZeroIndex;
    private TextView[] _textViews;

    public Game(TextView[] textViews) throws Exception {
        if(textViews.length != SIZE){
            throw new Exception("Text views array length must be size of: " + SIZE + ", got: " + textViews.length);
        }
        _textViews = textViews;
        _field = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 ,8 ,9, 10, 11, 12 ,13, 14, 15 };
        _cellZeroIndex = 0;
    }

    public void init(){
        this.shuffleField();
    }




    public void onMoveRight(){

        // From ["4"] [" "]
        // To   [" "] ["4"]
        //Log.i(TAG, "onMoveRight [cellZeroIndex]: " + _cellZeroIndex + " with index " + (_cellZeroIndex - 1) + "[" + _field[_cellZeroIndex - 1] + "]");


        if(_cellZeroIndex % BORDER_SIZE != 0){
            int temp = _field[_cellZeroIndex - 1];
            _field[_cellZeroIndex - 1] = 0;
            _field[_cellZeroIndex] = temp;
            setViewsTextToFieldValues();
            this.updateCellZeroIndex();
        }
    }

    // From [" "] ["4"]
    // To   ["4"] [" "]
    public void onMoveLeft(){
        //Log.i(TAG, "onMoveLeft [cellZeroIndex]: " + _cellZeroIndex + " with index " + (_cellZeroIndex + 1) + "[" + _field[_cellZeroIndex + 1] + "]");

        if((_cellZeroIndex + 1) % BORDER_SIZE != 0){
            int temp = _field[_cellZeroIndex + 1];
            _field[_cellZeroIndex + 1] = 0;
            _field[_cellZeroIndex] = temp;
            setViewsTextToFieldValues();
            this.updateCellZeroIndex();
        }
    }

    public void onMoveTop(){
        //Log.i(TAG, "onMoveTop [cellZeroIndex]: " + _cellZeroIndex + " with index " + (_cellZeroIndex + BORDER_SIZE) + "[" + _field[_cellZeroIndex + BORDER_SIZE] + "]");

        // 12 + 4 <= 16 - 1
        if((_cellZeroIndex + BORDER_SIZE) <= (SIZE - 1)){
            int temp = _field[_cellZeroIndex + BORDER_SIZE];
            _field[_cellZeroIndex + BORDER_SIZE] = 0;
            _field[_cellZeroIndex] = temp;
            setViewsTextToFieldValues();
            this.updateCellZeroIndex();
        }
    }
    // [0]  [1]  [2] [3]
    // [4]  [5]  [6] [7]
    // [8]  [9]  [10] [11]
    // [12] [13] [14] [15]


    public void onMoveBottom(){
        //Log.i(TAG, "onMoveBottom [cellZeroIndex]: " + _cellZeroIndex + " with index " + (_cellZeroIndex - BORDER_SIZE) + "[" + _field[_cellZeroIndex - BORDER_SIZE] + "]");

        if(_cellZeroIndex >= BORDER_SIZE){
            int temp = _field[_cellZeroIndex - BORDER_SIZE];
            _field[_cellZeroIndex - BORDER_SIZE] = 0;
            _field[_cellZeroIndex] = temp;
            setViewsTextToFieldValues();
            this.updateCellZeroIndex();
        }
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
        this.shuffle(_field);
        setViewsTextToFieldValues();
        this.updateCellZeroIndex();
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

    private void updateCellZeroIndex(){
        for(int i = 0; i < SIZE; i++){
            if(_field[i] == 0){
                this._cellZeroIndex = i;
                return;
            }
        }
        this._cellZeroIndex = -1;
    }
}
