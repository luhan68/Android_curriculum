package com.hanlu.youtuberecycleview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EmptyFragment extends Fragment {
    private static String ARG_SECTION_NUMBER="section_number";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_layout, container,false);
        return view;
    }

    public static EmptyFragment newInstance(int sectionNumber) {
        EmptyFragment emptyFragment = new EmptyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER,sectionNumber);
        emptyFragment.setArguments(args);
        return emptyFragment;
    }
}
