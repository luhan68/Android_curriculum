package com.hanlu.connect3_lvl1;

import android.annotation.SuppressLint;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private int player = 0;
    private int[] locations = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private TextView winnerMessage;// = (TextView)findViewById(R.id.gameOverText);
    private LinearLayout layout;//(LinearLayout)findViewById(R.id.playAgainLayout);
    private GridLayout gridLayout;//(GridLayout)findViewById(R.id.GridLayout);
    boolean gameActivated = true;
    private MediaPlayer laughPlayer;
    private MediaPlayer clickPlayer;
    private MediaPlayer failPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winnerMessage = findViewById(R.id.gameOverText);
        layout = findViewById(R.id.playAgainLayout);
        gridLayout = findViewById(R.id.GridLayout);
        layout.setVisibility(View.INVISIBLE);
        laughPlayer = MediaPlayer.create(this, R.raw.win);
        clickPlayer = MediaPlayer.create(this, R.raw.click);
        failPlayer = MediaPlayer.create(this, R.raw.fail);
    }


    @SuppressLint("SetTextI18n")
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tagCounter = Integer.parseInt(counter.getTag().toString());
        if (locations[tagCounter] == 2 && gameActivated) {
            locations[tagCounter] = player;
            counter.setTranslationY(-1000f);
            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                player = 1;
            } else if (player == 1) {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }
            counter.animate().translationYBy(1000f).rotation(540).setDuration(300);
            clickPlayer.start();
            boolean empty = false;
            for (int location : locations) {
                if (location == 2) {
                    empty = true;
                }
            }
            if (!empty) {
                winnerMessage.setText("A Tie.");
                layout.setVisibility(View.VISIBLE);
                failPlayer.start();
            }
            for (int[] winPosition : winningPosition) {
                if (locations[winPosition[0]] == locations[winPosition[1]] && locations[winPosition[2]]
                        == locations[winPosition[1]] && locations[winPosition[0]] != 2) {
                    gameActivated = false;
                    if (locations[winPosition[0]] == 0) {
                        winnerMessage.setText("Yellow has won");
                        layout.setVisibility(View.VISIBLE);
                        laughPlayer.start();
                    } else if (locations[winPosition[0]] == 1) {
                        winnerMessage.setText("Red has won");
                        layout.setVisibility(View.VISIBLE);
                        laughPlayer.start();
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        gameActivated = true;
        layout.setVisibility((View.INVISIBLE));
        player = 0;
        for (int i = 0; i < locations.length; i++)
            locations[i] = 2;
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


}
