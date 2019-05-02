package com.fanikiosoftware.mynews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
