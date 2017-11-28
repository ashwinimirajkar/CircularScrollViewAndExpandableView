package com.example.ashwini.android_assignment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<CardFragment> _fragments;

    public CardFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        _fragments = new ArrayList<>();

        for(int i = 0; i< CardFragment.mAds.length; i++){
            addCardFragment(new CardFragment());
        }
    }

    @Override
    public int getCount() {
        return _fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.getInstance(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object mFragment = super.instantiateItem(container, position);
        _fragments.set(position, (CardFragment) mFragment);
        return mFragment;
    }

    public void addCardFragment(CardFragment fragment) {
        _fragments.add(fragment);
    }

}
