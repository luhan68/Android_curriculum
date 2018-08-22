package com.hanlu.doordash_v2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class PagerAdapter extends FragmentPagerAdapter{
    public PagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return FoodFragment.newInstance(i);
        } else {
            return EmptyFragment.newInstance(i);
        }
    }

    @Override
    public int getCount() { return 3;
    }
}
