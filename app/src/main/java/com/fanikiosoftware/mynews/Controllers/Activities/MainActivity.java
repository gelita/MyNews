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
        // Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        // get the TabLayout from the layout_main
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        // attach TabLayout and ViewPager
        tabs.setupWithViewPager(pager);
        // for the purpose of design, tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
        // Set Adapter PageAdapter and glue it together
    }

//    create menu options
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
//  allow search icon to always show
    public void onSearch(MenuItem item){
        Intent i = new Intent(getBaseContext(), SearchActivity.class);
        startActivity(i);
    }
    public void onNotifications(MenuItem item){
        Intent i = new Intent(getBaseContext(), NotificationsActivity.class);
        startActivity(i);
    }
    public void onHelp(MenuItem item){
        Intent i = new Intent(getBaseContext(), HelpActivity.class);
        startActivity(i);
    }
    public void onAbout(MenuItem item){
        Intent i = new Intent(getBaseContext(), AboutActivity.class);
        startActivity(i);
    }
}

