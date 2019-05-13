package com.fanikiosoftware.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import icepick.Icepick;

public abstract class BaseFragment extends Fragment {

    //Force developer implement those methods
    protected abstract BaseFragment newInstance();

    protected abstract int getFragmentLayout();

    protected abstract void configureDesign();

    protected abstract void updateDesign();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Get layout identifier from abstract method declared in child class
        //this method will report the correct layout's identifier so the correct layout will be used
        View view = inflater.inflate(getFragmentLayout(), container, false);
        // Binding Views with ButterKnife support
        ButterKnife.bind(this, view);
        // 4 - Configure Design (Developer will call this method instead of override onCreateView())
        this.configureDesign();
        return (view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Handling Bundle Restoration
        Icepick.restoreInstanceState(this, savedInstanceState);
        // Update Design (Developer will call this method instead of override onActivityCreated())
        this.updateDesign();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Handling Bundle Save
        Icepick.saveInstanceState(this, outState);
    }
}