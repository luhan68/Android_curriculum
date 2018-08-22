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
import java.util.Scanner;

import static android.widget.LinearLayout.VERTICAL;

public class FoodFragment extends Fragment {
    private static String ARG_SECTION_NUMBER = "SECTION_NUMBER";
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);


        // CANNOT UNDERSTAND FOLLOWING LINES.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), VERTICAL));


        initializeFood(container.getContext().getAssets());
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(container.getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Food food = RecyclerAdapter.getFood(position);
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




    //title, intro, picture_url, name, info;
    public void initializeFood(AssetManager assetManager){
        InputStream inputStream;
        try{
            inputStream = assetManager.open("foods.txt");
            Scanner kb = new Scanner(inputStream);
            kb.nextLine();
            while (kb.hasNext()){
                String title = kb.nextLine();
                String intro = kb.nextLine();
                String picture_url = kb.nextLine();
                String name = kb.nextLine();
                String info = kb.nextLine();
                Food food = new Food(title, intro, picture_url, name, info);
                recyclerAdapter.addFood(food);
                kb.nextLine();
            }
            inputStream.close();
            kb.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        recyclerAdapter.notifyDataSetChanged();
    }
    public static FoodFragment newInstance(int sectionNumber) {
        FoodFragment foodFragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        foodFragment.setArguments(args);
        return foodFragment;
    }
}
