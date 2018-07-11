package com.hanlu.connect3_lvl2;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    private GridLayout board;
    private int player = 0;
    boolean gameActivated = true;
    public int[][] twoDArray = new int[][];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = findViewById(R.id.board);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                twoDArray[i][j] = 2;
        for (int i = 0; i < 9; i++){
            addToGrid(i);
        }

    }

    public void addToGrid(final int index) {
        ImageView icon = new ImageView(this);
        icon.setTag(2);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(310, 310);
        params.setMargins(20, 10, 11, 19);
        icon.setLayoutParams(params);
        icon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        board.addView(icon, index);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView dot = (ImageView) view;
                int tag = Integer.parseInt(view.getTag().toString());
                if (tag == 2 && gameActivated) {
                    view.setTag(player);
                    dot.setTranslationY(-1000f);
                    if (player == 0) {// yellow player
                        dot.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellow, null));
                        player = 1;
                        twoDArray[index % 3 - 1][index / 3] = 0;
                    } else if (player == 1) { // red player
                        dot.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.red, null));
                        player = 0;
                        twoDArray[index % 3 - 1][index / 3] = 1;
                    }
                    dot.animate().translationYBy(1000f).rotation(540).setDuration(300);
                }
                boolean empty = false;
                for (int[] i : twoDArray) {
                    for (int j : i) {
                        if (j == 2)
                            empty = true;
                    }
                }

            }
        });
    }


}

