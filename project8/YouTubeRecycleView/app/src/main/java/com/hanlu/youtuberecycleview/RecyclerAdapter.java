package com.hanlu.youtuberecycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Priority;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VideoViewHolder>{
    private ArrayList<Video> videos;
    private Context context;

    public RecyclerAdapter(){
        videos = new ArrayList<>();
    }

    public Video getVideo(int i) {
        return videos.get(i);
    }

    public void addVideo(Video video)
    {
        videos.add(video);
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View rowView = LayoutInflater.from(context).inflate(R.layout.row_design, parent, false);
        return new VideoViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        Video video = videos.get(i);
        //SET TEXT VIEWS
        videoViewHolder.video_title.setText(video.getVideo_title());
        videoViewHolder.author_name.setText(video.getAuthor_title());
        //PROFILE PHOTO
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(25f);
        circularProgressDrawable.setCenterRadius(100f);
        circularProgressDrawable.start();
        GlideApp.with(context)
                .load(video.getAuthor_title_url())
                .fitCenter()
                .placeholder(circularProgressDrawable)
                .priority(Priority.HIGH)
                .into(videoViewHolder.author_photo);
        //VIDEO PHOTO
        CircularProgressDrawable circularProgressDrawable2 = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(25f);
        circularProgressDrawable.setCenterRadius(100f);
        circularProgressDrawable.start();
        GlideApp.with(context)
                .load(video.getVideo_img_url())
                .fitCenter()
                .placeholder(circularProgressDrawable2)
                .priority(Priority.HIGH)
                .into(videoViewHolder.video_pic);
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
