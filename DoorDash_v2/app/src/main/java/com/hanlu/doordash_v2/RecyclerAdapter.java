package com.hanlu.doordash_v2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.FoodViewHolder> {

    private static ArrayList<Food> foods;
    private Context context;

    public RecyclerAdapter(){foods = new ArrayList<>();}
    public void addFood(Food food){foods.add(food);}
    public static Food getFood(int position){return foods.get(position);}



    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View rowView = LayoutInflater.from(context).inflate(R.layout.row_design,parent, false); // attach to root?
        return new FoodViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i) {
        Food food = foods.get(i);
        foodViewHolder.title.setText(food.getTitle());
        foodViewHolder.intro.setText(food.getIntro());
        foodViewHolder.name.setText(food.getName());
        foodViewHolder.info.setText(food.getInfo());
        Glide.with(context).load(food.getPicture_url()).into(foodViewHolder.picture_url);
    }
    

    @Override
    public int getItemCount() {
        return foods.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{
        TextView title, intro, name, info;
        ImageView picture_url;
        
        FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            intro = itemView.findViewById(R.id.intro);
            name = itemView.findViewById(R.id.name);
            info = itemView.findViewById(R.id.info);
            picture_url = itemView.findViewById(R.id.picture);

        }
    }
}
