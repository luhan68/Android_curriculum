package com.hanlu.doordash_v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class FoodActivity extends AppCompatActivity {

    private ArrayList<Integer> scores;
    private ArrayAdapter<Integer> arrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        listView = findViewById(R.id.list_item);
        scores = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores);
        listView.setAdapter(arrayAdapter);
        scores.addAll(Arrays.asList(1, 2, 3, 4, 5));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int score = scores.get(i);
                Toast.makeText(view.getContext(), score + " is selected!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
