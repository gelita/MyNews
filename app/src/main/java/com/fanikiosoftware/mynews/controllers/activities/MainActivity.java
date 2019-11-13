package com.fanikiosoftware.mynews.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.fragments.PagerAdapter;

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
        ViewPager viewPager = findViewById(R.id.viewpager);
        //Set PagerAdapter -adapter may change pages according to which tab is currently selected
        viewPager.setAdapter(new PagerAdapter((getSupportFragmentManager())));
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        // attach TabLayout and ViewPager
        tabLayout.setupWithViewPager(viewPager);
        // for the purpose of design, tabs have the same width
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    //create menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem backItem = menu.findItem(R.id.miBackPress);
        // hide the back arrow on this activity
        backItem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    //determine which item was selected and respond accordingly with the proper intent and int extra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        String title = "";
        switch (item.getItemId()) {
            case R.id.miAbout:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                title = "About";
                break;
            case R.id.miHelp:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                title = "Help";
                break;
            case R.id.miNotifications:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                title = "Notifications";
                break;
            case R.id.miSearch:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                title = "Search";
                break;
            default:
                break;
        }
        //add intent extra int to identify which button click called the activity
        intent.putExtra("title", title);
        startActivity(intent);
        //allow processing of menu item to carry on - return false per documentation
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}