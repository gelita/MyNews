package com.fanikiosoftware.mynews.controllers.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    //Default Constructor
    public PagerAdapter(FragmentManager mgr) {
        super(mgr);
    }

    //returns the number of tabs
    public int getCount() {
        return (3);
    }

    //returns a new object of the selected fragment type
    public Fragment getItem(int position) {
        MyFragment myFragment = MyFragment.newInstance(position);
        return myFragment;
    }


    //returns the title of the selected tab
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Top Stories";
            case 1:
                return "Business";
            case 2:
                return "Tech News";
            default:
                return null;
        }
    }
}