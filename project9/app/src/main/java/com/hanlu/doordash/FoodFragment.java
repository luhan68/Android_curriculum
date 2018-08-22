package com.hanlu.doordash;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FoodFragment extends Fragment {
    private ArrayList<Food> foods;
    private ArrayAdapter<Food> arrayAdapter;
    private Button seeAllButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.row_design, container, false);
        seeAllButton = view.findViewById(R.id.button);
        seeAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(container.getContext(),SeeAllActivity.class);
                startActivity(intent);
            }
        });
        ListView listView = view.findViewById(R.id.list_view);
        foods = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, foods);
        listView.setAdapter(arrayAdapter);
        initializeFood(container.getContext().getAssets());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Food food = foods.get(i);
                Toast.makeText(container.getContext(), food.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    //title, intro, picture_url, name, info
    public void initializeFood(AssetManager assetManager) {
        InputStream inputStream;
        try {
            inputStream = assetManager.open("food.txt");
            Scanner kb = new Scanner(inputStream);
            kb.nextLine();
            while (kb.hasNext()) {
                String title = kb.nextLine();
                String intro = kb.nextLine();
                String picture_url = kb.nextLine();
                String name = kb.nextLine();
                String info = kb.nextLine();
                Food food = new Food(title, intro, picture_url, name, info);
                foods.add(food);
                kb.nextLine();
            }
            inputStream.close();
            kb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrayAdapter.notifyDataSetChanged();
    }

    public static FoodFragment newInstance(int sectionNumber) {
        FoodFragment foodFragment = new FoodFragment();
        Bundle args = new Bundle();
        String ARG_SECTION_NUMBER = "SECTION_NUMBER";
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        foodFragment.setArguments(args);
        return foodFragment;
    }
}
