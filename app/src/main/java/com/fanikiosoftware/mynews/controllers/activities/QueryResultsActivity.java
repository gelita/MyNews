package com.fanikiosoftware.mynews.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.fragments.MyFragment;


public class QueryResultsActivity extends AppCompatActivity {

    public static final String TAG = "QueryResultsActivity";
    public String BASE_URL;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "  :: onCreate called");
        setContentView(R.layout.activity_main);
        getBaseUrl();
        Log.d(TAG, BASE_URL);
//        MyFragment myFragment = MyFragment.newInstance(6, getBaseUrl());
    }

//    public void onCreateView(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d(TAG, "  :: onCreateView called");
//        setContentView(R.layout.activity_query_results);
//        getBaseUrl();
//        MyFragment myFragment = MyFragment.newInstance(6, BASE_URL);
//    }

    private String getBaseUrl() {
        Intent intent = getIntent();
        BASE_URL = intent.getStringExtra("BASE_URL");
        return BASE_URL;
    }
}