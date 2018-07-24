package com.hanlu.guessthatfood;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> foods;
    private ArrayList<String> eattenFoods;
    private ImageView picture;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView rounds;
    private int score;
    private int totalRounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foods = new ArrayList<>();
        totalRounds = 1;
        picture = findViewById(R.id.picture);
        button1 = findViewById(R.id.option1);
        button2 = findViewById(R.id.option2);
        button3 = findViewById(R.id.option3);
        button4 = findViewById(R.id.option4);
        rounds = findViewById(R.id.rounds);
        readFile();
        eattenFoods = new ArrayList<>(foods);
        Log.i("MyActivity","eattenFoods is" + eattenFoods);
        setImageView(eattenFoods.get(getRandom()));

    }

    public void isMatch(View v) {
        Button b = (Button) v;
        String buttonText = getFormattedString(b.getText().toString());
        String pictureText = picture.getTag().toString();
        if (buttonText.equals(pictureText))
            score++;
        if (totalRounds <= 4) {
            rounds.setText(totalRounds + " / 4");
            setImageView(eattenFoods.get(getRandom()));
        }else
            alertView("Game Over","Score is " + score + ".", "Play Again");
    }

    void alertView(String title, String message, String buttonM) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        score = 0;
        totalRounds = 1;
        eattenFoods = new ArrayList<>(foods);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, buttonM,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        setImageView(eattenFoods.get(getRandom()));
                        rounds.setText("1 / 4");
                    }
                });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void setButtonName(){
        Collections.shuffle(foods);
        button1.setText(foods.get(0));
        button2.setText(foods.get(1));
        button3.setText(foods.get(2));
        button4.setText(foods.get(3));
    }

    public int getRandom(){
        Random random = new Random(new Date().getTime());
        int index = random.nextInt(eattenFoods.size());
        return index;
    }

    public void readFile(){
        AssetManager assetManager = getAssets();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("Food.txt");
            Scanner kb = new Scanner(inputStream);
            while (kb.hasNext()) {
                foods.add(kb.nextLine());
            }
            inputStream.close();
            kb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Drawable getDrawable(String name) {
        Context context = this;
        int resourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        Log.i("MyActivity","eattenFoods are" + eattenFoods);
        return context.getResources().getDrawable(resourceId);
    }

    public String getFormattedString(String s){
        s = s.toLowerCase();
        s = s.replaceAll(" ", "");
        return s;
    }

    public void setImageView(String name){
        String oName = name;
        name = getFormattedString(name);
        picture.setTag(name);
        picture.setImageDrawable(getDrawable(name));
        eattenFoods.remove(oName);
        setButtonName();
        totalRounds++;
    }
}

