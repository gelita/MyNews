package com.fanikiosoftware.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.fanikiosoftware.mynews.Controllers.Fragments.PageAdapter;
import com.fanikiosoftware.mynews.R;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Configure ViewPager
        this.configureViewPager();
    }

    //this method provides the connection between the ViewPager and the TabLayout
    private void configureViewPager() {
        // Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        // Set PagerAdapter to the ViewPager
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        // get the TabLayout from the main layout
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        // attach TabLayout and ViewPager
        tabs.setupWithViewPager(pager);
        // for the purpose of design, tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
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
                intent.putExtra("title", 10);
                break;
            case R.id.miHelp:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                intent.putExtra("title", 20);
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