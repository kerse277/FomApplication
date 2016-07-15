package com.fom.msesoft.fomapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fom.msesoft.fomapplication.fragment.FriendListFragment;
import com.fom.msesoft.fomapplication.fragment.FriendListFragment_;

import com.fom.msesoft.fomapplication.fragment.ProfileFragment;
import com.fom.msesoft.fomapplication.fragment.ProfileFragmentOne;
import com.fom.msesoft.fomapplication.fragment.ProfileFragmentOne_;
import com.fom.msesoft.fomapplication.fragment.ProfileFragmentTwo;
import com.fom.msesoft.fomapplication.fragment.ProfileFragmentTwo_;
import com.fom.msesoft.fomapplication.fragment.ProfileFragment_;

public class ProfilePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ProfilePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProfileFragmentOne tab1 = new ProfileFragmentOne_();
                return tab1;
            case 1:
                ProfileFragmentTwo tab2 = new ProfileFragmentTwo_();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}