package com.aneeqshah.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    boolean isGameActive = true;

    //0 = x, 1 = o;
    int counter = 0;

    //2 = not played yet
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    //winning positions
    int[][] win = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void drop(View v){
        ImageView image = (ImageView)v;
        int tag = Integer.parseInt(image.getTag().toString());

        if(gameState[tag] == 2 && isGameActive) {

            gameState[tag] = counter;

            if (counter == 0) {
                image.setImageResource(R.drawable.one);
                counter = 1;

            } else {
                image.setImageResource(R.drawable.two);
                counter = 0;
            }

            image.setTranslationY(-1000f);

            image.animate().translationYBy(1000f).setDuration(300);
        }

        for(int[] winningPos: win){

            if(gameState[winningPos[0]] == gameState[winningPos[1]] &&
                    gameState[winningPos[1]] == gameState[winningPos[2]] &&
                    gameState[winningPos[0]] != 2){

                isGameActive = false;
                String winner = "Player 2";

                if(gameState[winningPos[0]] == 0){
                    winner = "Player 1";
                }


                TextView winMessage = findViewById(R.id.winPopOut);
                winMessage.setText(winner + " has won");

                LinearLayout layout = findViewById(R.id.winLayout);
                layout.setVisibility(View.VISIBLE);
            }
            else{
                boolean gameOver = true;

                for(int counterState : gameState){
                    if(counterState == 2) gameOver = false;
                }

                if(gameOver){
                    TextView winMessage = findViewById(R.id.winPopOut);
                    winMessage.setText("It's a DRAW!!!");
                    LinearLayout layout = findViewById(R.id.winLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){

        isGameActive = true;

        LinearLayout layout = findViewById(R.id.winLayout);
        layout.setVisibility(View.INVISIBLE);

        counter = 0;

        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }
}
