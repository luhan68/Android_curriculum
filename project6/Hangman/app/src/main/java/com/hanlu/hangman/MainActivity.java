package com.hanlu.hangman;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.audiofx.Equalizer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private TextView hangmanImage;
    private TextView showWord;
    private TextView wrongText;
    private EditText guessEdit;
    private Button guessButton;
    private ArrayList<String> wordsList;
    private Random random;
    private ArrayList<String> wrongLetters, rightLetters;

    private int lives;
    private String hangMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hangmanImage = findViewById(R.id.hangman);
        wrongText = findViewById(R.id.guess_letter);
        showWord = findViewById(R.id.guess_word);
        guessEdit = findViewById(R.id.guess_edit);
        guessButton = findViewById(R.id.guess_button);
        random = new Random(new Date().getTime());
        wordsList = readWords(getAssets());
        hangMan = newWord();
        lives = 6;
        guessButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String s = guessEdit.getText().toString();
                if (hangMan.contains(s)) {
                    rightLetters.add(s);
                    replaceWords(rightLetters, hangMan);
                } else {
                    wrongLetters.add(s);
                    wrongText.setText((CharSequence) wrongLetters);
                    lives--;
                }
                if (!(showWord.getText().toString().contains("_"))) {
                    alertView("You win", "Remaining lives is " + lives + ".", "Play Again");
                }else if (lives == 0) {
                    alertView("Game Over", "Answer " + hangMan + ".", "Play Again");
                }
                drawMan(lives);
            }
        });

    }

    public void replaceWords(ArrayList<String> letter, String word) {
            for (int index = 0; index < word.length(); index++)
            {
                if(!letter.contains(Character.toString(word.charAt(index)))) {
                    word = word.substring(0, index) + "_" + word.substring(index + 1);
                }
            }
            showWord.setText(word);
    }

    public void resetGame() {
        StringBuilder beginWord = null;
        lives = 6;
        wordsList = readWords(getAssets());
        hangMan = newWord();
        wrongLetters = new ArrayList<>();
        wrongText.setText("");
        rightLetters = new ArrayList<>();
        for (int i = 0; i < hangMan.length(); i++)
            beginWord.append("_");
        assert beginWord != null;
        showWord.setText(beginWord.toString());
        drawMan(lives);
    }


    void alertView(String title, String message, String buttonM) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, buttonM,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public String newWord() {
        int n = random.nextInt(wordsList.size());
        String word = wordsList.get(n);
        return word;
    }

    public ArrayList<String> readWords(AssetManager assetManager) {
        ArrayList<String> words = new ArrayList<>();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("words.txt");
            Scanner kb = new Scanner(inputStream);
            while (kb.hasNext()) {
                words.add(kb.nextLine());
            }
            inputStream.close();
            kb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }


    public void drawMan(int lives) {
        Resources res = getResources();
        String[] baselist = res.getStringArray(R.array.base_list);
        hangmanImage.setText(baselist[lives]);

    }
}
