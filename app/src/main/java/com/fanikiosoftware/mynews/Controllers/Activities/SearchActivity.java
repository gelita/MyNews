package com.fanikiosoftware.mynews.Controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fanikiosoftware.mynews.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
    }

    //create menu options
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();

        switch (item.getItemId()) {
            case R.id.miAbout:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                break;
            case R.id.miHelp:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                break;
            case R.id.miNotifications:
                intent = new Intent(getBaseContext(), NotificationsActivity.class);
                break;
            case R.id.miSearch:
                intent = new Intent(getBaseContext(), SearchActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
        //allow processing of menu item to carry on - return false per documentation
        return false;
    }
}
