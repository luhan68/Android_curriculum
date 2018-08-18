package com.hanlu.youtuberecycleview;

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

import static android.support.v7.widget.LinearLayoutManager.*;

public class VideoFragment extends Fragment {
    private static String ARG_SECTION_NUMBER = "SECTION_NUMBER";
    private RecyclerView recyclerView;
    private RecyclerAdapter videosAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        videosAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(videosAdapter);
        //SETUP DISPLAY FOR RECYCLE VIEW
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), VERTICAL));  //getActivity
        return view;
    }

    //TO BE CALLED FROM ANOTHER CLASS
    public static VideoFragment newInstance(int sectionNumber){
        VideoFragment videoFragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        videoFragment.setArguments(args);
        return videoFragment;
    }
}
