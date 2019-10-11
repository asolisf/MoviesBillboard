package com.alansolisflores.movies.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.alansolisflores.movies.fragments.PopularFragment;
import com.alansolisflores.movies.fragments.TopRankedFragment;
import com.alansolisflores.movies.fragments.UpcomingFragment;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public SectionsPagerAdapter(FragmentManager fragmentManager,int numberOfTabs){
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new PopularFragment();
            case 1:
                return new TopRankedFragment();
            case 2:
                return new UpcomingFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
