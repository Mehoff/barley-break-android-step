package com.step.barley_breakstep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.step.barley_breakstep.classes.Game;
import com.step.barley_breakstep.classes.OnSwipeTouchListener;

public class MainActivity extends AppCompatActivity {

    TextView[] textViews = new TextView[16];
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.mainLayout).setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()){
            @Override
            public void onSwipeRight(){
                game.onMoveRight();
            }
            @Override
            public void onSwipeLeft(){
                game.onMoveLeft();
            }
            @Override
            public void onSwipeTop(){
                game.onMoveTop();
            }
            @Override
            public void onSwipeBottom(){
                game.onMoveBottom();
            }
        });
    }

    private void initGame() throws Exception {
        TextView cell1 = findViewById(R.id.cell_1);
        TextView cell2 = findViewById(R.id.cell_2);
        TextView cell3 = findViewById(R.id.cell_3);
        TextView cell4 = findViewById(R.id.cell_4);
        TextView cell5 = findViewById(R.id.cell_5);
        TextView cell6 = findViewById(R.id.cell_6);
        TextView cell7 = findViewById(R.id.cell_7);
        TextView cell8 = findViewById(R.id.cell_8);
        TextView cell9 = findViewById(R.id.cell_9);
        TextView cell10 = findViewById(R.id.cell_10);
        TextView cell11 = findViewById(R.id.cell_11);
        TextView cell12 = findViewById(R.id.cell_12);
        TextView cell13 = findViewById(R.id.cell_13);
        TextView cell14 = findViewById(R.id.cell_14);
        TextView cell15 = findViewById(R.id.cell_15);
        TextView cell0 = findViewById(R.id.cell_0);

        textViews[0] = cell1;
        textViews[1] = cell2;
        textViews[2] = cell3;
        textViews[3] = cell4;
        textViews[4] = cell5;
        textViews[5] = cell6;
        textViews[6] = cell7;
        textViews[7] = cell8;
        textViews[8] = cell9;
        textViews[9] = cell10;
        textViews[10] = cell11;
        textViews[11] = cell12;
        textViews[12] = cell13;
        textViews[13] = cell14;
        textViews[14] = cell15;
        textViews[15] = cell0;

        game = new Game(this, textViews);
        game.init();

        Button buttonWin = findViewById(R.id.btn_win);
        buttonWin.setOnClickListener((e) -> {
            game.setupWinCondition();
        });

        Button buttonShuffle = findViewById(R.id.btn_shuffle);
        buttonShuffle.setOnClickListener((e) -> {
            game.shuffleField();
        });
    }
}