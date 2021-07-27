package com.example.tictaketogo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] gameStae = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositations = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}, {2,5,8}, {1,4,7}, {0,3,6}};
    int activePlayer = 0;
    boolean gameActive = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameStae[tappedCounter] == 2 && gameActive) {


            gameStae[tappedCounter] = activePlayer;
            counter.setTranslationX(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.hehe);
                activePlayer = 0;
            }
            counter.animate().translationXBy(1500).rotationY(3600).setDuration(600);
            for (int[] winningPosition : winningPositations) {
                if (gameStae[winningPosition[0]] == gameStae[winningPosition[1]] && gameStae[winningPosition[1]] == gameStae[winningPosition[2]] && gameStae[winningPosition[0]] != 2) {
                    //if someone won
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "yellow";

                    } else {
                        winner = "red";
                    }

                    Button playAgain = (Button) findViewById(R.id.playAgainbutton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won");
                    playAgain.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void PlayAgain(View view){
        Button playAgain = (Button) findViewById(R.id.playAgainbutton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgain.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(1);
            counter.setImageDrawable(null);
        }
        for(int i=0; i<gameStae.length; i++){
            gameStae[i] =1 ;
        }

         activePlayer = 0;
         gameActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}