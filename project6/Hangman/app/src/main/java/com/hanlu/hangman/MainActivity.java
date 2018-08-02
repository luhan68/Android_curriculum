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
        resetGame();
        guessButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (rightLetters.contains(showWord.getText().toString()) || wrongLetters.contains((showWord.getText().toString())))
                    alertView1("Alert!", "You have already entered the letter.", "Try another");
                else if (showWord.getText().toString().matches("[a-zA-Z]+"))
                    alertView1("Alert!", "Please enter a letter.", "Try another");
                else {
                    replaceWords();
                    drawMan(lives);
                    guessEdit.setText("");
                }
                if (!(showWord.getText().toString().contains("_")))
                    alertView2("You win", "Remaining lives is " + lives + ".", "Play Again");
                if (lives == 0)
                    alertView2("Game Over", "The answer is " + hangMan + ".", "Play Again");
            }
        });

    }

    public void replaceWords() {
        String s = guessEdit.getText().toString();
        if (hangMan.contains(s))
            rightLetters.add(s);
        else {
            wrongLetters.add(s);
            lives--;
        }

        showWord.setText("");
        for (int index = 0; index < hangMan.length(); index++) {
            String x = hangMan.substring(index, index + 1);
            if (rightLetters.contains(x)) {
                showWord.append(hangMan.substring(index, index + 1));
            } else {
                showWord.append("_");
                if (index != hangMan.length() - 1)
                    showWord.append(" ");
            }
        }
        wrongText.setText("");
        for (String c : wrongLetters) {
            wrongText.append(c);
            if (wrongLetters.indexOf(c) != wrongLetters.size() - 1)
                wrongText.append(",");
        }
        // for (int index = 0; index < word.length(); index++) {
        //     if (!letter.contains(Character.toString(word.charAt(index)))) {
        //         word = word.substring(0, index) + "_" + word.substring(index + 1);
        //     }
        // }
        // showWord.setText(word);

    }

    public void resetGame() {
        lives = 6;
        wrongText.setText("");
        guessEdit.setText("");
        wordsList = readWords(getAssets());
        hangMan = newWord();
        wrongLetters = new ArrayList<>();
        rightLetters = new ArrayList<>();
        replaceWords();
//        showWord.setText("");
//        for (int i = 0; i < hangMan.length(); i++) {
//            showWord.append("_");
//            if (i != hangMan.length() - 1)
//                showWord.append(" ");
//        }
        drawMan(lives);
    }


    void alertView1(String title, String message, final String buttonM) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, buttonM,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        guessEdit.setText("");
                    }
                });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    void alertView2(String title, String message, final String buttonM) {
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
        return wordsList.get(n);
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
