package com.fanikiosoftware.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanikiosoftware.mynews.Controllers.Activities.MyAdapter;
import com.fanikiosoftware.mynews.R;

import icepick.Icepick;

public abstract class BlankFragment extends Fragment {

    //Force developer implement those methods
    protected abstract BlankFragment newInstance();

    protected abstract int getFragmentLayout();

    protected abstract void configureDesign();

    protected abstract void updateDesign();

    public BlankFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Get activity_query identifier from abstract method declared in child class
        //this method will report the correct activity_query's identifier so the correct activity_query will be used
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_recycler_view);
        recyclerView.setHasFixedSize(true);
        MyAdapter adapter = new MyAdapter(new String[]{"One", "Two", "Three", "Four", "Five",
                "Six", "Seven", "Eight"});
        recyclerView.setAdapter(adapter);
        //the LinearLayoutManager manages the recyclerView list
        LinearLayoutManager linLayoutMgr = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linLayoutMgr);
        return rootView;
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