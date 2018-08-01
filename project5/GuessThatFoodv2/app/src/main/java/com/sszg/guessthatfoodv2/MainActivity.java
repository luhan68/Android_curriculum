package com.sszg.guessthatfoodv2;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Priority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.picture)
    ImageView picture;
    @BindViews({R.id.option1, R.id.option2, R.id.option3, R.id.option4})
    List<Button> buttonList;
    //Foods List
    private ArrayList<String> foods, chosen;
    private HashMap<String, String> foodURLS;
    private int numOfRounds, round, gameScore;
    private Random random;
    private String correctAnswer;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //ButterKnife.apply(buttonList, ENABLED, false);
        random = new Random(new Date().getTime());
        getImageURLS(false);
    }

    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(@NonNull View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };

    static final ButterKnife.Setter<View, List<String>> CHANGE_TEXT = new ButterKnife.Setter<View, List<String>>() {
        @Override
        public void set(@NonNull View view, List<String> values, int index) {
            ((Button) view).setText(values.get(index));
        }
    };

    @OnClick({R.id.option1, R.id.option2, R.id.option3, R.id.option4, R.id.hint})
    public void onClick(View view) {
        if (!gameOver) {
            score.setText(String.valueOf(round + "/" + numOfRounds));
            String selectedAnswer = ((Button) view).getText().toString();
            String answer = correctAnswer;//picture.getTag().toString();
            if (!selectedAnswer.equalsIgnoreCase("hint")) {
                if (selectedAnswer.equalsIgnoreCase(answer)) {
                    //Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show();
                    gameScore++;
                } else {
                    //Toast.makeText(this, "WRONG!", Toast.LENGTH_SHORT).show();
                }
                if (round == numOfRounds) {
                    gameOver = true;
                    alertView("Your score is " + gameScore + "/" + numOfRounds, this, new Callable<Void>() {
                        @Override
                        public Void call() {
                            resetGame();
                            return null;
                        }
                    });
                } else {
                    newRound();
                }
            } else {
                Toast.makeText(this, "HINT IS...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void resetGame() {
        chosen.clear();
        gameScore = 0;
        round = 0;
        correctAnswer = "";
        random = new Random(new Date().getTime());
        gameOver = false;
        newRound();
    }

    public void newRound() {
        score.setText(String.valueOf(round + "/" + numOfRounds));
        ArrayList<String> choices = getChoices();
        String imageName = choices.get(choices.size() - 1);
        Collections.shuffle(choices);
        ButterKnife.apply(buttonList, CHANGE_TEXT, choices);
        String imageURL = foodURLS.get(imageName);
        setImageViewFromURL(imageURL, imageName);
        round++;
    }

    public ArrayList<String> getChoices() {
        foods.removeAll(chosen);
        int index = random.nextInt(foods.size());
        String answer = foods.get(index);
        foods.remove(index);
        foods.addAll(chosen);
        ArrayList<String> randomChoices = pickNRandomElements(foods, 3);
        randomChoices.add(answer);
        chosen.add(answer);
        foods.add(answer);
        return randomChoices;
    }

    public <E> ArrayList<E> pickNRandomElements(ArrayList<E> list, int n) {
        int length = list.size();

        if (length < n) return null;

        //We don't need to shuffle the whole list
        for (int i = length - 1; i >= length - n; --i) {
            Collections.swap(list, i, random.nextInt(i + 1));
        }
        return new ArrayList<>(list.subList(length - n, length));
    }

    public void setImageViewFromDrawable(String imageName) {
        imageName = imageName.toLowerCase();
        imageName = imageName.replaceAll(" ", "");
        int imageId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        picture.setImageResource(imageId);
        picture.setTag(imageName);
        correctAnswer = imageName;
    }

    public void setImageViewFromURL(String url, String name) {
        name = name.toLowerCase();
        name = name.replaceAll(" ", "");
        correctAnswer = name;
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStrokeWidth(25f);
        circularProgressDrawable.setCenterRadius(100f);
        circularProgressDrawable.start();
        GlideApp.with(this)
                .load(url)
                .fitCenter()
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_cloud_off_red)
                .priority(Priority.HIGH)
                .into(picture);
    }


    public void setFoodURLS(ArrayList<String> foodURLS) {
        this.foodURLS = new HashMap<>();
        for (int i = 0; i < foods.size(); i++) {
            this.foodURLS.put(foods.get(i), foodURLS.get(i));
        }
        for (String s : foodURLS) {
            System.out.println(s);
        }
        numOfRounds = 5;
        round = 0;
        ButterKnife.apply(buttonList, ENABLED, true);
        newRound();
    }

    private void alertView(String message, Context context, final Callable<Void> resetGame) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Game Over")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                        try {
                            resetGame.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
    }

    public void getImageURLS(boolean downloadURLS) {
        chosen = new ArrayList<>();
        foods = HelperClass.readFoods(getAssets());
        if (downloadURLS) {
            ImageSearchApi imageSearchApi = new ImageSearchApi(this);
            imageSearchApi.execute(foods);
        } else {
            setFoodURLS(HelperClass.readFoodURLS(getAssets()));
        }
    }


}
