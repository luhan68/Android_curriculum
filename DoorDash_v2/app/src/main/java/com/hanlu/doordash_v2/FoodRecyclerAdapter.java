package com.hanlu.doordash_v2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.widget.LinearLayout.HORIZONTAL;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder> {

    private static ArrayList<Food> foods;
    private Context context;


    public FoodRecyclerAdapter(){foods = new ArrayList<>();}
    public void addFood(Food food){foods.add(food);}
    public static Food getFood(int position){return foods.get(position);}



    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View rowView = LayoutInflater.from(context).inflate(R.layout.row_design,parent, false);
        return new FoodViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i) {
        Food food = foods.get(i);
        foodViewHolder.title.setText(food.getTitle());
        foodViewHolder.type.setText(food.getType());
        foodViewHolder.time.setText(food.getTime());
        foodViewHolder.delivery_fee.setText(food.getDelivery_fee());
        initializeRecyclerView(foodViewHolder.pictureRecyclerView, context);
        foodViewHolder.pictureRecyclerAdapter.setPicturesList(food.getPicture_url());
    }


    private void initializeRecyclerView(RecyclerView recyclerView, Context context) {
        //SETUP DISPLAY FOR RECYCLE VIEW
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, HORIZONTAL));  //getActivity
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder{
        public TextView title, type, time, delivery_fee;
        private RecyclerView pictureRecyclerView;
        private PictureRecyclerAdapter pictureRecyclerAdapter;
        
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            title = itemView.findViewById(R.id.title);
            type= itemView.findViewById(R.id.type);
            time = itemView.findViewById(R.id.time);
            delivery_fee = itemView.findViewById(R.id.delivery_fee);
            pictureRecyclerView = itemView.findViewById(R.id.picture_recycler_view);
            pictureRecyclerAdapter = new PictureRecyclerAdapter();
            pictureRecyclerView.setAdapter(pictureRecyclerAdapter);

        }
    }
}
