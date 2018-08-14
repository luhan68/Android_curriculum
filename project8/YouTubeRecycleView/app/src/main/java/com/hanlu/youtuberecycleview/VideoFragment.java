package com.hanlu.youtuberecycleview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VideoFragment extends Fragment {
    private static String ARG_SECTION_NUMBER = "SECTION_NUMBER";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.row_design,container,false);
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
