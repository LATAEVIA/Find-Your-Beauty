package com.lataeviaberry.mybeauty.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lataeviaberry.mybeauty.models.Beauty;
import com.lataeviaberry.mybeauty.ui.BeautyDetailFragment;

import java.util.ArrayList;

public class BeautyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Beauty> mBeautys;

    public BeautyPagerAdapter(FragmentManager fm, ArrayList<Beauty> beautys) {
        super(fm);
        mBeautys = beautys;
    }

    @Override
    public Fragment getItem(int position) {
        return BeautyDetailFragment.newInstance(mBeautys.get(position));
    }

    @Override
    public int getCount() {
        return mBeautys.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBeautys.get(position).getName();
    }
}