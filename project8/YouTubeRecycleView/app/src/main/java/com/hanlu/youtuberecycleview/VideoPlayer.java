package com.hanlu.youtuberecycleview;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class VideoPlayer extends AppCompatActivity {
    private VideoView videoView;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        videoView = findViewById(R.id.video_player);
        progressDialog = new ProgressDialog(VideoPlayer.this);
        progressDialog.setMessage("Loading Video");
        progressDialog.show();

        Uri uri = Uri.parse("https://www.bilibili.com/video/av29645571");
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
            }
        });
    }


}
