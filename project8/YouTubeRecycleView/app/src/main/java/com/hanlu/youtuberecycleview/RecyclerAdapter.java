package com.hanlu.youtuberecycleview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VideoViewHolder>{
    private ArrayList<Video> videos;

    public RecyclerAdapter(){
        videos = new ArrayList<>();
    }

    public void addVideo(String title, String author_name, String video_pic, String author_photo)
    {
        Video video = new Video(author_name, title, author_photo, video_pic);
        videos.add(video);
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design, parent, false);
        return new VideoViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        Video video = videos.get(i);
        videoViewHolder.video_title.setText(video.getVideoTitle());
        videoViewHolder.author_name.setText(video.getAuthorName());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    class VideoViewHolder extends RecyclerView.ViewHolder{
        TextView author_name, video_title;
        ImageView author_photo, video_pic;

        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            video_title = itemView.findViewById(R.id.video_name);
            video_pic= itemView.findViewById(R.id.video_pic);
            author_name = itemView.findViewById(R.id.author_name);
            author_photo = itemView.findViewById(R.id.author_photo);
        }
    }
}
