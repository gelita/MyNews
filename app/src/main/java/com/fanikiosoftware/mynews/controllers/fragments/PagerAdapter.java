package com.fanikiosoftware.mynews.controllers.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fanikiosoftware.mynews.controllers.utility.Constants;

public class PagerAdapter extends FragmentPagerAdapter {


    //Default Constructor
    public PagerAdapter(FragmentManager mgr) {
        super(mgr);
    }

    //returns the number of tabs
    public int getCount() {
        return (6);
    }

    //returns a new object of the selected fragment type
    public Fragment getItem(int position) {
        return MyFragment.newInstance(position);
    }

    //returns the title of the selected tab
    public String getPageTitle(int position) {
        return Constants.titles[position];
    }
}