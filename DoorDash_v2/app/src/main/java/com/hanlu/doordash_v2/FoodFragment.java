package com.hanlu.doordash_v2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

public class FoodFragment extends Fragment {
    private static String ARG_SECTION_NUMBER = "SECTION_NUMBER";
    private RecyclerView foodRecyclerView;
    private FoodRecyclerAdapter foodRecyclerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment, container, false);
        foodRecyclerView = view.findViewById(R.id.food_recycler_view);
        foodRecyclerAdapter = new FoodRecyclerAdapter();
        foodRecyclerView.setAdapter(foodRecyclerAdapter);



        initializeRecyclerView(container);
        initializeObject(container.getContext().getAssets());
        foodRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(container.getContext(), foodRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Food food = FoodRecyclerAdapter.getFood(position);
                Toast.makeText(container.getContext(), food.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(container.getContext(), FoodActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }


    public void initializeRecyclerView(ViewGroup container){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        foodRecyclerView.setLayoutManager(layoutManager);
        foodRecyclerView.setItemAnimator(new DefaultItemAnimator());
        foodRecyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), VERTICAL));


    }


    //title, intro, picture_url, name, info;
    public void initializeObject(AssetManager assetManager){
        InputStream inputStream;
        try{
            inputStream = assetManager.open("foods.txt");
            Scanner kb = new Scanner(inputStream);
            kb.nextLine();
            while (kb.hasNext()){
                ArrayList<String> pictureList = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    String picture = kb.nextLine();
                    pictureList.add(picture);
                }
                String title = kb.nextLine();
                String type = kb.nextLine();
                String reviews = kb.nextLine();
                String time = kb.nextLine();
                String delivery_fee = kb.nextLine();
                Food food = new Food(title, type, reviews, time, delivery_fee, pictureList);
                foodRecyclerAdapter.addFood(food);
                kb.nextLine();
            }
            inputStream.close();
            kb.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        foodRecyclerAdapter.notifyDataSetChanged();
    }


    public static FoodFragment newInstance(int sectionNumber) {
        FoodFragment foodFragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        foodFragment.setArguments(args);
        return foodFragment;
    }
}
