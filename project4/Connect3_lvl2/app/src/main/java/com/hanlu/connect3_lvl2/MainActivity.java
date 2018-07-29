package com.hanlu.connect3_lvl2;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {

    private GridLayout board;
    private int player = 0;
    boolean gameActivated = true;
    public int[][] twoDArray = new int[3][3];
    private MediaPlayer laughPlayer;
    private MediaPlayer clickPlayer;
    private MediaPlayer failPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = findViewById(R.id.board);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                twoDArray[i][j] = 2;
        for (int i = 0; i < 9; i++) {
            addToGrid(i);
        }
        laughPlayer = MediaPlayer.create(this, R.raw.win);
        clickPlayer = MediaPlayer.create(this, R.raw.click);
        failPlayer = MediaPlayer.create(this, R.raw.fail);
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
                        twoDArray[index / 3][index % 3] = 0;
                    } else if (player == 1) { // red player
                        dot.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.red, null));
                        twoDArray[index / 3][index % 3] = 1;
                    }
                    dot.animate().translationYBy(1000f).rotation(540).setDuration(100);
                }
                clickPlayer.start();
                if (winGame(player, index % 3, index / 3) == -1) {
                    alertView("Game Over","Draw", "Ok" );
                    failPlayer.start();
                } else if (winGame(player, index % 3, index / 3) == 1) {
                    alertView("Game Over","Red Wins", "Ok" );
                    laughPlayer.start();
                } else if (winGame(player, index % 3, index / 3) == 0) {
                    alertView("Game Over","Yellow Wins", "Ok" );
                    laughPlayer.start();
                }

                if(player == 1)
                    player = 0;
                else
                    player = 1;
            }

            void alertView(String title, String message, String buttonM) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(title);
                alertDialog.setMessage(message);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, buttonM,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                renewGrid();
                                for (int i = 0; i < 3; i++)
                                    for (int j = 0; j < 3; j++)
                                        twoDArray[i][j] = 2;
                                gameActivated = true;
                            }
                        });
                alertDialog.setCancelable(false);
                alertDialog.show();
            }


            int winGame(int player, int col, int prow) {

                int empty = -1;

                if (checkRightDiagnol(player) || checkLeftDiagnol(player) || checkCol(player, col) || checkRow(player, prow))
                    return player;
                else {
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 3; j++)
                            if (twoDArray[i][j] == 2)
                                empty = 2;
                    return empty;
                }
            }

            void renewGrid(){
                player = 1;
                for (int i = 0; i < 9; i++)
                {
                    ((ImageView) board.getChildAt(i)).setImageDrawable(null);
                    board.getChildAt(i).setTag(2);
                }
            }

            boolean checkRow(int player, int prow) {
                for (int i = 0; i < 3; i++) {
                    if (twoDArray[prow][i] != player)
                        return false;
                }
                return true;
            }

            boolean checkCol(int player, int col) {
                for (int i = 0; i < 3; i++) {
                    if (twoDArray[i][col] != player)
                        return false;
                }
                return true;
            }


            boolean checkLeftDiagnol(int player) {
                for (int i = 0; i < 3; i++) {
                    if (twoDArray[i][i] != player)
                        return false;
                }
                return true;
            }

            boolean checkRightDiagnol(int player) {
                for (int i = 0; i < 3; i++) {
                    if (twoDArray[2 - i][i] != player)
                        return false;
                }
                return true;
            }
        });

    }
}



