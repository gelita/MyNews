package com.fanikiosoftware.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fanikiosoftware.mynews.R;

public  class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info);
        TextView textView = findViewById(R.id.tvFragmentInfo);
        this.setTitle("About");
        textView.setText(R.string.lorem_ipsum);
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
