package com.hanlu.youtuberecycleview;

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

import static android.support.v7.widget.LinearLayoutManager.*;

public class VideoFragment extends Fragment {
    private static String ARG_SECTION_NUMBER = "SECTION_NUMBER";
    private RecyclerView recyclerView;
    private RecyclerAdapter videosAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        videosAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(videosAdapter);
        //SETUP DISPLAY FOR RECYCLE VIEW
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), VERTICAL));  //getActivity
        initializefilm(container.getContext().getAssets());
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(container.getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Video video = videosAdapter.getVideo(position);
                Toast.makeText(container.getContext(), video.getVideo_title() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(container.getContext(), VideoPlayer.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }

    public void initializefilm(AssetManager assetManager) {
        InputStream inputStream;
        try {
            inputStream = assetManager.open("url.txt");
            Scanner kb = new Scanner(inputStream);
            kb.nextLine();
            while (kb.hasNext()) {
                String imageURL = kb.nextLine();
                String videoURL = kb.nextLine();
                String authorURL = kb.nextLine();
                String authorTitle = kb.nextLine();
                String videoTitle = kb.nextLine();
                Video video = new Video(imageURL, videoURL, authorURL, authorTitle, videoTitle);
                videosAdapter.addVideo(video);
                kb.nextLine();
            }
            inputStream.close();
            kb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        videosAdapter.notifyDataSetChanged();
    }

    //TO BE CALLED FROM ANOTHER CLASS
    public static VideoFragment newInstance(int sectionNumber) {
        VideoFragment videoFragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        videoFragment.setArguments(args);
        return videoFragment;
    }
}
