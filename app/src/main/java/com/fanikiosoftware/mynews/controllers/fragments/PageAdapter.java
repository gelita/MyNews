package com.fanikiosoftware.mynews.controllers.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    //returns the number of tabs
    public int getCount() {
        return (3);
    }

    //returns a new object of the selected fragment type
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TopFragment();
            case 1:
                return new PopularFragment();
            case 2:
                return new TechFragment();
            default:
                return null;
        }
    }

    //returns the title of the selected tab
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Top Stories";
            case 1:
                return "Popular";
            case 2:
                return "Tech News";
            default:
                return null;
        }
    }
}