package com.fanikiosoftware.mynews.Controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fanikiosoftware.mynews.R;

public class QueryActivity extends AppCompatActivity {

    int tag;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        getActivityTitle();
        setTitle(title);
    }

    //create menu options
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();
        //create tag identifier for each menu item
        tag = 0;
        switch (item.getItemId()) {
            case R.id.miAbout:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                tag = 10;
                break;
            case R.id.miHelp:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                tag = 20;
                break;
            case R.id.miNotifications:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                tag = 30;
                break;
            case R.id.miSearch:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                tag = 40;
                break;
            default:
                break;
        }
        //add intent extra int to identify which button click called the activity
        intent.putExtra("tag", tag);
        startActivity(intent);
        //allow processing of menu item to carry on - return false per documentation
        return false;
    }

    public String getActivityTitle() {
        Intent intent = getIntent();
        tag = intent.getIntExtra("tag", 0);
        if (tag == 30) {
            title = "Notifications";
        } else {
            title = "Search";
        }
        return title;
    }
}
