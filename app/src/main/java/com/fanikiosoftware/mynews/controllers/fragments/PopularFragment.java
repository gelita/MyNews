package com.fanikiosoftware.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.activities.MyAdapter;

import icepick.Icepick;
import icepick.State;

public class PopularFragment extends Fragment {

    @State
    int buttonTag;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

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

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Handling Bundle Restoration
        Icepick.restoreInstanceState(this, savedInstanceState);
        // Update Design (Developer will call this method instead of override onActivityCreated())
        this.updateDesign();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Handling Bundle Save
        Icepick.saveInstanceState(this, outState);
    }
    protected Fragment newInstance() {
        return new PopularFragment();
    }

    protected int getFragmentLayout() {
        return R.layout.fragment_detail;
    }

    protected void configureDesign() {
    }

    protected void updateDesign() {
        //  this.updateTextView(this.buttonTag);
    }
}