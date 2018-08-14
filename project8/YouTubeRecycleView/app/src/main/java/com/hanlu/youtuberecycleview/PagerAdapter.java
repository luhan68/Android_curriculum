package com.hanlu.youtuberecycleview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i){
        if (i == 1) {
            return VideoFragment.newInstance(i);
        }else {
            return EmptyFragment.newInstance(i);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}