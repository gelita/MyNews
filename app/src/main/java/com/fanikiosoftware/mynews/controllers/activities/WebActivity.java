package com.fanikiosoftware.mynews.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.fanikiosoftware.mynews.R;

class WebActivity extends AppCompatActivity {

    private String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView webView = findViewById(R.id.webView);
        url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
    }
}