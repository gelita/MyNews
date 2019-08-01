package com.fanikiosoftware.mynews.controllers.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.fragments.SearchFragment;
import com.fanikiosoftware.mynews.controllers.utility.Constants;

import java.util.ArrayList;

public class QueryResultsActivity extends AppCompatActivity {

    public static final String TAG = "QueryResultsActivity";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate started");
        setContentView(R.layout.activity_query_results);
        ArrayList<String> userQueryList = getIntent().getStringArrayListExtra(Constants.USER_QUERY_LIST);
        String startDate = getIntent().getStringExtra(Constants.DATE_START);
        String endDate = getIntent().getStringExtra(Constants.DATE_END);
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        SearchFragment searchFragment = SearchFragment.newInstance(
                6, userQueryList, startDate, endDate);
        ft.replace(R.id.fragment_holder, searchFragment);
        // Complete the changes added above
        ft.commit();
    }
}