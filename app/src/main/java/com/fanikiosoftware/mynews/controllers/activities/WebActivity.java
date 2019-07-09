package com.fanikiosoftware.mynews.controllers.activities;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.fanikiosoftware.mynews.R;

public class WebActivity extends AppCompatActivity {


    private static final String TAG = "WebActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_webview);
        WebView webView = findViewById(R.id.webView);
        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
    }

    //create menu options
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();
        String t = "";
        switch (item.getItemId()) {
            case R.id.miBackPress:
                intent = new Intent(getBaseContext(), MainActivity.class);
                break;
            case R.id.miAbout:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                t = "About";
                break;
            case R.id.miHelp:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                t = "Help";
                break;
            case R.id.miNotifications:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                t = "Notifications";
                break;
            case R.id.miSearch:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                t = "Search";
                break;
            default:
                break;
        }
        //add intent extra int to identify which button click called the activity
        intent.putExtra("title", t);
        startActivity(intent);
        //allow processing of menu item to carry on - return false per documentation
        return false;
    }
}