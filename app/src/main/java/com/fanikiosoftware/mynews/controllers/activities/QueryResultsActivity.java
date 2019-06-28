package com.fanikiosoftware.mynews.controllers.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.fragments.MyFragment;


public class QueryResultsActivity extends AppCompatActivity {

    public static final String TAG = "QueryResultsActivity";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "  :: onCreate started");
        setContentView(R.layout.activity_query_results);
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        MyFragment myFragment = MyFragment.newInstance(6, getUserQuery());
        ft.replace(R.id.fragment_holder, myFragment); // or ft.replace?
        // Complete the changes added above
        ft.commit();
    }

    private String getUserQuery() {
        return getIntent().getStringExtra("userQuery");
    }
}