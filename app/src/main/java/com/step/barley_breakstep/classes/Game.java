package com.step.barley_breakstep.classes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Debug;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.step.barley_breakstep.MainActivity;

import java.util.Arrays;
import java.util.Random;

public class Game {

    private final int BORDER_SIZE = 4;
    private final int SIZE = BORDER_SIZE * BORDER_SIZE;
    private final String TAG = "MyApp";
    private int[] _field;
    private int _cellZeroIndex;
    private AppCompatActivity _activity;
    private TextView[] _textViews;

    public Game(AppCompatActivity activity, TextView[] textViews) throws Exception {
        if(textViews.length != SIZE){
            throw new Exception("Text views array length must be size of: " + SIZE + ", got: " + textViews.length);
        }
        _activity = activity;
        _textViews = textViews;
        _field = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 ,8 ,9, 10, 11, 12 ,13, 14, 15 };
        _cellZeroIndex = 0;
    }

    public void init(){
        this.shuffleField();
    }


    public void onMoveRight(){
        if(_cellZeroIndex % BORDER_SIZE != 0){
            int temp = _field[_cellZeroIndex - 1];
            _field[_cellZeroIndex - 1] = 0;
            _field[_cellZeroIndex] = temp;
            makeMove();
        }
    }
    public void onMoveLeft(){
        if((_cellZeroIndex + 1) % BORDER_SIZE != 0){
            int temp = _field[_cellZeroIndex + 1];
            _field[_cellZeroIndex + 1] = 0;
            _field[_cellZeroIndex] = temp;
            makeMove();
        }
    }

    public void onMoveTop(){
        if((_cellZeroIndex + BORDER_SIZE) <= (SIZE - 1)){
            int temp = _field[_cellZeroIndex + BORDER_SIZE];
            _field[_cellZeroIndex + BORDER_SIZE] = 0;
            _field[_cellZeroIndex] = temp;
            makeMove();
        }
    }

    public void onMoveBottom(){
        if(_cellZeroIndex >= BORDER_SIZE){
            int temp = _field[_cellZeroIndex - BORDER_SIZE];
            _field[_cellZeroIndex - BORDER_SIZE] = 0;
            _field[_cellZeroIndex] = temp;
            makeMove();
        }
    }

    public void setupWinCondition(){
        _field = new int[]
        { 1, 2, 3, 4, 5, 6, 7 ,8 ,9, 10, 11, 12 ,13, 14, 0, 15 };
        makeMove();
    }

    private void makeMove(){
        setViewsTextToFieldValues();
        this.updateCellZeroIndex();
        if(this.checkWin()){
            Win();
        }
    }

    private void Win(){
        AlertDialog.Builder builder = new AlertDialog.Builder(_activity);
        builder.setMessage("Want to replay?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Replay();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Nothing or close app
            }
        });

        builder.create().show();
    }

    private void Replay() {
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

    public void shuffleField(){
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

    private boolean checkWin(){
        for(int i = 0; i < SIZE - 1; i++){
            Log.i(TAG,"_field[i]: " + _field[i]);
            Log.i(TAG,"[i + 1]: " + (i + 1));

            if(_field[i] != (i + 1)){
                Log.i(TAG,"NOT WIN!");
                return false;
            }
        }
        Log.i(TAG,"WIN!");
        return true;
    }
}
