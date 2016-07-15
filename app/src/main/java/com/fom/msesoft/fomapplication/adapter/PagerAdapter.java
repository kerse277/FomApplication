package com.fom.msesoft.fomapplication.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fom.msesoft.fomapplication.fragment.FriendListFragment;
import com.fom.msesoft.fomapplication.fragment.FriendListFragment_;
import com.fom.msesoft.fomapplication.fragment.ThreeFragment;
import com.fom.msesoft.fomapplication.fragment.ThreeFragment_;
import com.fom.msesoft.fomapplication.fragment.TwoFragment;
import com.fom.msesoft.fomapplication.fragment.TwoFragment_;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FriendListFragment tab1 = new FriendListFragment_();
                return tab1;
            case 1:
                TwoFragment tab2 = new TwoFragment_();
                return tab2;
            case 2:
                ThreeFragment tab3 = new ThreeFragment_();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}