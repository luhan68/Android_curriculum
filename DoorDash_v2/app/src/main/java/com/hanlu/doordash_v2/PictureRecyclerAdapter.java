package com.hanlu.doordash_v2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PictureRecyclerAdapter extends RecyclerView.Adapter<PictureRecyclerAdapter.PictureViewHolder> {
    private ArrayList<String> picturesList;
    private Context context;
    private int mRowIndex = -1;

    public PictureRecyclerAdapter() {
    }

    public void setPicturesList(ArrayList<String> pictures)
    {
        if (picturesList != pictures)
        {
            picturesList = pictures;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        picturesList = FoodRecyclerAdapter.getFood(i).getPicture_url();
        View rowView = LayoutInflater.from(context).inflate(R.layout.picture_design, viewGroup, false);

        return new PictureViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder viewHolder, int i) {
        PictureViewHolder holder = viewHolder;
        Glide.with(context).load(picturesList.get(i)).into(holder.picturesView);
        holder.itemView.setTag(i);

    }

    @Override
    public int getItemCount() {
        return picturesList.size();
    }

    class PictureViewHolder extends RecyclerView.ViewHolder {
        public ImageView picturesView;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);
            picturesView = itemView.findViewById(R.id.picture_recycler_view);
        }
    }

}

