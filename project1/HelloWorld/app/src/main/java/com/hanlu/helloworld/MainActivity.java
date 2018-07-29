package com.hanlu.helloworld;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView cookieImage;
    private TextView cookieCounter;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cookieImage = findViewById(R.id.cookie);
        cookieCounter = findViewById(R.id.counter);

        String ans = "click button : " + num;
        cookieCounter.setText(ans);

        cookieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationY", 100f, 0f);
                animY.setDuration(700);
                animY.setInterpolator(new BounceInterpolator());
                animY.start();

                num++;
                String ans = "click button : " + num;
                cookieCounter.setText(ans);
            }
        });
    }
}
