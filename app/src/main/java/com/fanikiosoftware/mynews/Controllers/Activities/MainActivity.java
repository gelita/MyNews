package com.fanikiosoftware.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.fanikiosoftware.mynews.Controllers.Fragments.PageAdapter;
import com.fanikiosoftware.mynews.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Configure ViewPager
        this.configureViewPager();
    }

    //this method provides the connection between the ViewPager and the TabLayout
    private void configureViewPager() {
        // Get ViewPager from activity_query
        ViewPager viewPager = findViewById(R.id.viewpager);
        // Set PagerAdapter to the ViewPager
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        // get the TabLayout from the main activity_query
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        // attach TabLayout and ViewPager
        tabLayout.setupWithViewPager(viewPager);
        // for the purpose of design, tabs have the same width
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    //create menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //determine which item was selected and respond accordingly with the proper intent and int extra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();
        //create tag identifier for each menu item
        int tag = 0;
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

    @Override
    public void onResume() {
        super.onResume();
    }
}